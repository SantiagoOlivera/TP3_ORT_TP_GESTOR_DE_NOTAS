package com.ort.tp_ort_tp3_app_gestordenotas.repositories

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Administrador
import com.ort.tp_ort_tp3_app_gestordenotas.entities.AnioMateria
import com.ort.tp_ort_tp3_app_gestordenotas.entities.EstadoMateria
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Estudiante
import com.ort.tp_ort_tp3_app_gestordenotas.entities.EstudianteMateria
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Materia
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Persona
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Usuario
import java.util.Date

class UsuariosRepository {

    companion object{

        fun getPersonas():MutableList<Persona>{
            var ret: MutableList<Persona> = mutableListOf();

            //Persona 1
            var p1: Persona = Persona(
                "XX.XXX.XXX",
                "Cosme",
                "Fulanito",
                Date(1991,9,21)
            );

            //Admin 1
            var p2: Persona = Persona(
                "00.000.000",
                "Administrador",
                "Papa",
                Date(1980,12,31)
            )

            ret.add(p1);
            ret.add(p2);

            return ret;
        }

        fun getUsuarios(): MutableList<Usuario>{

            var ret: MutableList<Usuario> = mutableListOf();
            //Materias
            var materias: MutableList<Materia> = getMaterias();
            //Personas
            var personas: MutableList<Persona> = getPersonas();


            var u1: Estudiante = Estudiante("USER1", "user1@text.com", "123", personas[0]);
            var u2: Usuario = Administrador("ADMIN1", "admin1@text.com", "123", personas[1]);


            for (m: Materia in materias){
                u1.agregarEstudianteMateria(EstudianteMateria("", EstadoMateria.PENDIENTE, 0));
            }

            ret.add(u1);
            ret.add(u2);

            return ret;
        }

        fun getEstudiantes():MutableList<Estudiante> {
            var ret: MutableList<Estudiante> = mutableListOf();
            //Estudiantes
            var estudiantes: List<Usuario> = getUsuarios().filter { e -> e is Estudiante };

            if(estudiantes.count() > 0){
                ret = estudiantes as MutableList<Estudiante>;
            }

            return ret;
        }

        fun getMaterias(): MutableList<Materia>{
            var ret: MutableList<Materia> = mutableListOf(
                //1°1°
                Materia("OE","Organización Empresarial","", AnioMateria.PRIMER_ANIO_PRIMER_CUATRIMESTRE),
                Materia("II","Introducción a la Informática","", AnioMateria.PRIMER_ANIO_PRIMER_CUATRIMESTRE),
                Materia("FP","Fundamentos de Programación","", AnioMateria.PRIMER_ANIO_PRIMER_CUATRIMESTRE),
                Materia("THP","Taller de Herramientas de Programación","", AnioMateria.PRIMER_ANIO_PRIMER_CUATRIMESTRE),
                Materia("MAT","Matemática","", AnioMateria.PRIMER_ANIO_PRIMER_CUATRIMESTRE),
                Materia("INT","Inglés Técnico","", AnioMateria.PRIMER_ANIO_PRIMER_CUATRIMESTRE),
                Materia("TCI","Taller de Creatividad e Innovación","", AnioMateria.PRIMER_ANIO_PRIMER_CUATRIMESTRE),
                //1°2°
                Materia("SA","Sistemas Administrativos","", AnioMateria.PRIMER_ANIO_SEGUNDO_CUATRIMESTRE),
                Materia("ASO","Arquitectura y Sistemas Operativos","", AnioMateria.PRIMER_ANIO_SEGUNDO_CUATRIMESTRE),
                Materia("P1","Programación I","", AnioMateria.PRIMER_ANIO_SEGUNDO_CUATRIMESTRE),
                Materia("TP1","Taller de Programación I","", AnioMateria.PRIMER_ANIO_SEGUNDO_CUATRIMESTRE),
                Materia("PNT1","Programación en Nuevas Tecnologías I","", AnioMateria.PRIMER_ANIO_SEGUNDO_CUATRIMESTRE),
                Materia("BD1","Base de Datos I","", AnioMateria.PRIMER_ANIO_SEGUNDO_CUATRIMESTRE),
                //2°1°
                Materia("AMS","Análisis y Metodología de Sistemas","", AnioMateria.SEGUNDO_ANIO_PRIMER_CUATRIMESTRE),
                Materia("BD2","Base de Datos II","", AnioMateria.SEGUNDO_ANIO_PRIMER_CUATRIMESTRE),
                Materia("P2","Programación II","", AnioMateria.SEGUNDO_ANIO_PRIMER_CUATRIMESTRE),
                Materia("TP2","Taller de Programación II","", AnioMateria.SEGUNDO_ANIO_PRIMER_CUATRIMESTRE),
                Materia("PNT2","Programación en Nuevas Tecnologías II","", AnioMateria.SEGUNDO_ANIO_PRIMER_CUATRIMESTRE),
                //2°2°
                Materia("P3","Programación III","", AnioMateria.SEGUNDO_ANIO_SEGUNDO_CUATRIMESTRE),
                Materia("TP3","Taller de Programación III","", AnioMateria.SEGUNDO_ANIO_SEGUNDO_CUATRIMESTRE),
                Materia("PF","Proyecto Final","", AnioMateria.SEGUNDO_ANIO_SEGUNDO_CUATRIMESTRE),
                Materia("SIS","Seguridad e Integridad de Sistemas","", AnioMateria.SEGUNDO_ANIO_SEGUNDO_CUATRIMESTRE),
                Materia("CS","Calidad de Software","", AnioMateria.SEGUNDO_ANIO_SEGUNDO_CUATRIMESTRE),
                Materia("EJ","Estudios Judaicos","", AnioMateria.SEGUNDO_ANIO_SEGUNDO_CUATRIMESTRE),
            );
            return ret;
        }

        fun login(usuarioOEmail: String, password: String): Usuario? {

            //test();
            //getMaterias();

            var usuarios: MutableList<Usuario> = getUsuarios();

            var u: Usuario? = usuarios.find { u: Usuario ->
                ( u.getUsuario() == usuarioOEmail || u.getEmail() == usuarioOEmail ) &&
                        u.getPassword() == password
            };

            return u;
        }

        fun test(){
            //val db = Firebase.firestore
            val db = Firebase.firestore
            db.collection("test")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        Log.d(TAG, "test ----- ID: ${document.id} ---DATA:${document.data}")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents.", exception)
                }

            db.collection("Personas")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        Log.d(TAG, "test ----- ID: ${document.id} ---DATA:${document.data}")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents.", exception)
                }

            db.collection("Usuarios")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        Log.d(TAG, "test ----- ID: ${document.id} ---DATA:${document.data}")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents.", exception)
                }
        }
    }
}