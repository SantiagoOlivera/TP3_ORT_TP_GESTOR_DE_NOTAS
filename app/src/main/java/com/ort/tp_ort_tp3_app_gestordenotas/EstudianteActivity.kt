package com.ort.tp_ort_tp3_app_gestordenotas

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.gms.common.internal.safeparcel.SafeParcelable
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ort.tp_ort_tp3_app_gestordenotas.entities.AnioMateria
import com.ort.tp_ort_tp3_app_gestordenotas.entities.EstadoMateria
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Estudiante
import com.ort.tp_ort_tp3_app_gestordenotas.entities.EstudianteMateria
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Materia
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Persona
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Usuario
import java.io.Serializable
import java.util.Date
import kotlin.reflect.safeCast


class EstudianteActivity : AppCompatActivity() {

    private lateinit var bottomNavView: BottomNavigationView
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var v: View
    private lateinit var estudiante: Estudiante;


    fun getEstudiante(): Estudiante{
        return this.estudiante;
    }

    fun getPersona(){

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estudiante);
        this.v = findViewById(android.R.id.content);


        this.navHostFragment = supportFragmentManager.findFragmentById(R.id.estudiante_navgraph) as NavHostFragment;
        this.bottomNavView = findViewById(R.id.bottom_bar);

        NavigationUI.setupWithNavController(this.bottomNavView, this.navHostFragment.navController);

    }



    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onStart() {
        super.onStart();


        var usuario: String? = intent.getStringExtra("usuario") ?: "";
        var email: String? = intent.getStringExtra("email") ?: "";
        var password: String? = intent.getStringExtra("password") ?: "";
        var idPersona: String? = intent.getStringExtra("idPersona") ?: "";

        this.estudiante = Estudiante(usuario.toString(), email.toString(), password.toString(), idPersona.toString() );

        if (idPersona != null) {
            val db = Firebase.firestore
            db.collection("Personas")
                .document(idPersona)
                .get()
                .addOnSuccessListener { document ->

                    var dni: String = document.data?.get("dni") as String;
                    var nombre: String = document.data?.get("nombre") as String;
                    var apellido: String = document.data?.get("apellido") as String;
                    //var fechaDeNacimiento: Date = Date(2023,10,21);
                    var fechaDeNacimiento: Date = (document.data?.get("fechaDeNacimiento") as Timestamp).toDate()

                    val p: Persona = Persona(idPersona, dni, nombre, apellido, fechaDeNacimiento);

                    //Snackbar.make(this.v, "RESULT: ${nombre}", Snackbar.LENGTH_LONG).show();

                    this.estudiante?.setPersona(p);

                    Snackbar.make(this.v, "RESULT: ${p.getIdPersona()}", Snackbar.LENGTH_LONG).show();

                    db.collection("EstudianteMateria")
                        .whereEqualTo("idPersona", p.getIdPersona())
                        .get()
                        .addOnSuccessListener {  result ->
                            for(document in result) {

                                var idMateria: String = document.get("idMateria") as String;
                                var estado: EstadoMateria = EstadoMateria.entries.get((document.get("estado") as Number).toInt());
                                var nota: Int = ( document.get("nota") as Number ).toInt();

                                Snackbar.make(this.v, "PersonaMateria: ${idMateria}", Snackbar.LENGTH_LONG).show();

                                db.collection("Materias")
                                    .document(idMateria)
                                    .get()
                                    .addOnSuccessListener{ materia ->

                                        Snackbar.make(this.v, "Materia: ${idMateria}", Snackbar.LENGTH_LONG).show();

                                        var descripcion: String =  materia.get("descripcion") as String;
                                        var nombre: String =  materia.get("nombre") as String;
                                        var anioMateria: AnioMateria = AnioMateria.entries.get((materia.get("anioMateria") as Number).toInt());


                                        var m: Materia = Materia(idMateria, nombre, descripcion, anioMateria);
                                        var em: EstudianteMateria = EstudianteMateria(this.estudiante, m, estado, nota);


                                        this.estudiante.agregarEstudianteMateria(em);
                                    }
                            }
                        }
                }
        }


        Snackbar.make(this.v, "Estudiante", Snackbar.LENGTH_LONG).show();


    }








}









