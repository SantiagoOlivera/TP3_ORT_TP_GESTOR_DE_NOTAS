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
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ort.tp_ort_tp3_app_gestordenotas.entities.AnioMateria
import com.ort.tp_ort_tp3_app_gestordenotas.entities.EstadoMateria
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Estudiante
import com.ort.tp_ort_tp3_app_gestordenotas.entities.EstudianteMateria
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Materia
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Persona
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Usuario
import com.ort.tp_ort_tp3_app_gestordenotas.factories.Factory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.io.Serializable
import java.util.Date
import kotlin.reflect.safeCast


class EstudianteActivity : AppCompatActivity() {

    private lateinit var bottomNavView: BottomNavigationView
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var v: View
    private lateinit var estudiante: Estudiante;
    private lateinit var factory: Factory;


    fun getEstudiante(): Estudiante{
        return this.estudiante;
    }

    fun getPersona(){

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estudiante);


        this.factory = Factory();

        this.v = findViewById(android.R.id.content);


        this.navHostFragment = supportFragmentManager.findFragmentById(R.id.estudiante_navgraph) as NavHostFragment;
        this.bottomNavView = findViewById(R.id.bottom_bar);

        NavigationUI.setupWithNavController(this.bottomNavView, this.navHostFragment.navController);




    }



    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onStart() {
        super.onStart();

        var view: View = this.v;
        Snackbar.make(this.v, "Estudiante", Snackbar.LENGTH_LONG).show();

        var m: Materia? = null;

        val parentJob = Job();
        val scope: CoroutineScope = CoroutineScope(Dispatchers.Default + parentJob);

        scope.launch {
            var materias: MutableList<Materia>? = factory.getListMaterias();
            //var estudiantes: MutableList<Estudiante>? = factory.getEstudiantes();
            //Snackbar.make(view, "Est: ${estudiantes?.get(0)?.getPersona()?.getNombreCompleto()}", Snackbar.LENGTH_LONG).show();

            var e: Estudiante? = factory.getEstudiante("egiKhZztEGpYKB3AcZbO");

            //Snackbar.make(view, "Est: ${e?.getPersona()?.getNombreCompleto()}", Snackbar.LENGTH_LONG).show();
        }



    }


    private suspend fun getMateria(idMateria: String): Materia?{
        var ret: Materia? = null;
        val db = Firebase.firestore;

        var materia: DocumentSnapshot = db.collection("Materias")
            .document(idMateria)
            .get()
            .await();

        if(materia.data != null){

            var idMateria: String = materia.id;
            var descripcion: String =  materia.get("descripcion") as String;
            var nombre: String =  materia.get("nombre") as String;
            var anioMateria: AnioMateria = AnioMateria.entries.get((materia.get("anioMateria") as Number).toInt());

            ret = Materia(idMateria, nombre, descripcion, anioMateria);
        }

        //.addOnSuccessListener { materia ->
        //var m: Materia = this.getMateria(materia);
        //this.estudiante.agregarEstudianteMateria(em);
        //}

        return ret;
    }

}















