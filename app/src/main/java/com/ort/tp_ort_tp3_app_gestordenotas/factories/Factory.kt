package com.ort.tp_ort_tp3_app_gestordenotas.factories

import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Administrador
import com.ort.tp_ort_tp3_app_gestordenotas.entities.AnioMateria
import com.ort.tp_ort_tp3_app_gestordenotas.entities.EstadoMateria
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Estudiante
import com.ort.tp_ort_tp3_app_gestordenotas.entities.EstudianteMateria
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Materia
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Persona
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Rol
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Usuario
import kotlinx.coroutines.tasks.await
import java.util.Date

class Factory {

    private lateinit var db: FirebaseFirestore

    constructor() {
        this.db = Firebase.firestore
    }

    suspend fun getEstudiantes(): MutableList<Estudiante>{

        var ret: MutableList<Estudiante> = mutableListOf();

        var data: QuerySnapshot? = this.db.collection("Usuarios")
            .whereEqualTo("rol", Rol.ESTUDIANTE.ordinal)
            .get()
            .await();

        if(data != null){
            if(data.documents.count() > 0){
                data.documents.forEach { u ->
                    var e: Estudiante? = this.getEstudiante(u.id);
                    if(e != null) {
                        ret.add(e);
                    }
                };
            }
        }

        return ret;
    }
    suspend fun getUsuarios(): MutableList<Usuario>? {
        var ret: MutableList<Usuario>? = null;
        return ret;
    }
    suspend fun getUsuario(userOrEmail: String, password: String): Usuario? {
        var ret: Usuario? = null;

        var result: QuerySnapshot? = this.db.collection("Usuarios")
            .whereEqualTo("usuario", userOrEmail)
            .whereEqualTo("password", password)
            .get()
            .await()

        if(result != null){
            if(!result.isEmpty){
                var id: String = result.documents.get(0).id;
                ret = this.getUsuario(id);
            }
        }


        return ret;
    }

    suspend fun getEstudiante(idDocument: String): Estudiante? {
        var ret: Estudiante? = this.getUsuario(idDocument) as Estudiante;
        if(ret != null){
            this.agregarListEstudianteMateria(ret);
        }
        return ret;
    }

    suspend fun getUsuario(idDocument:String): Usuario? {

        var ret: Usuario? = null;

        var u: DocumentSnapshot = this.db.collection("Usuarios")
            .document(idDocument)
            .get()
            .await();

        if(u.exists()) {

            var usuario: String = u.get("usuario") as String;
            var email: String = u.get("email") as String;
            var password: String = u.get("password") as String;
            var idPersona: String = u.get("idPersona") as String;
            var rol: Rol = Rol.entries.get( ( u.get("rol") as Number ).toInt() );

            if(rol == Rol.ESTUDIANTE){
                ret = Estudiante(usuario, email, password, idPersona);
            } else if(rol == Rol.ADMINISTRADOR){
                ret = Administrador(usuario, email, password, idPersona);
            }

            var p: Persona? = this.getPersona(idPersona);

            if(ret != null){
                if(p != null) {
                    ret.setPersona(p);
                }
            }
        }

        return ret;
    }

    suspend fun agregarListEstudianteMateria(e: Estudiante): Estudiante {

        var ret: Estudiante = e;

        var materias: MutableList<Materia> = this.getListMaterias();

        var idPersona: String = e.getPersona().getIdPersona();

        var documents: QuerySnapshot = this.db.collection("EstudianteMateria")
            .whereEqualTo("idPersona", idPersona)
            .get()
            .await()

        if(documents != null){
            if(!documents.isEmpty){
                for(d in documents) {

                    var idMateria: String = d.get("idMateria") as String;
                    var nota: Int = ( d.get("nota") as Number ).toInt();
                    var isInscripto: Boolean = d.get("isInscripto") as Boolean;
                    var estado: EstadoMateria = EstadoMateria.entries.get((d.get("estado") as Number).toInt());

                    var m: Materia? = materias.find { m -> m.getId() == idMateria }

                    if(m != null) {
                        var em: EstudianteMateria = EstudianteMateria(e, m, estado, nota );
                        em.setIsInscripto(isInscripto);

                        ret.agregarEstudianteMateria(em);
                    }
                }
            }
        }


        return ret;
    }


    suspend fun getMateria(idMateria: String): Materia? {
        var ret: Materia? = null;
        return ret;
    }

    suspend fun getListMaterias(): MutableList<Materia> {
        var ret: MutableList<Materia> = mutableListOf();

        var data: QuerySnapshot? = this.db.collection("Materias")
            .get()
            .await();

        if(data != null){
            if(!data.isEmpty){
                for(document in data) {

                    var idMateria: String = document.id;
                    var nombre: String =  document.get("nombre") as String;
                    var descripcion: String =  document.get("descripcion") as String;
                    var anioMateria: AnioMateria = AnioMateria.entries.get((document.get("anioMateria") as Number).toInt());

                    var m: Materia = Materia(idMateria, nombre, descripcion, anioMateria);

                    ret.add(m);

                }
            }
        }

        return ret;
    }

    suspend fun getPersona(idPersona: String): Persona? {
        var ret: Persona? = null;

        var document: DocumentSnapshot = this.db.collection("Personas")
            .document(idPersona)
            .get()
            .await();

        if(document.exists()) {

            var dni: String = document.get("dni") as String;
            var nombre: String = document.get("nombre") as String;
            var apellido: String = document.get("apellido") as String;
            var fechaDeNacimiento: Date = (document.get("fechaDeNacimiento") as Timestamp).toDate();

            ret = Persona(idPersona, dni, nombre, apellido, fechaDeNacimiento);

        }

        return ret;
    }


    suspend fun setEstudiante(e: Estudiante){
        this.setPersona(e.getPersona());
        this.setUsuario(e as Usuario);
    }

    suspend fun setUsuario(u: Usuario) {
        var rol: Int = 0;

        if(u is Estudiante){
            rol = Rol.ESTUDIANTE.ordinal;
        }else if(u is Administrador){
            rol = Rol.ESTUDIANTE.ordinal;
        }

        val usuario = hashMapOf(
            "usuario" to u.getUsuario(),
            "email" to u.getEmail(),
            "password" to u.getPassword(),
            "idPersona" to u.getPersona().getIdPersona()
        )

        this.db.collection("Usuarios")
            .add(usuario)
            .await()
            .get()
            .await();

    }

    suspend fun setPersona(p: Persona){

        var fn: Timestamp = Timestamp(p.getFechaDeNacimiento());

        val persona = hashMapOf(
            "dni" to p.getNombre(),
            "apellido" to p.getApellido(),
            "fechaDeNacimiento" to fn,
            "imagen" to "",
        );

        var doc: DocumentSnapshot = this.db.collection("Personas")
            .add(persona)
            .await()
            .get()
            .await();

        var idPersona: String = doc.id;

        p.setIdPersona(idPersona);

    }

    suspend fun setEstudianteMateria(e: Estudiante) {

    }

}