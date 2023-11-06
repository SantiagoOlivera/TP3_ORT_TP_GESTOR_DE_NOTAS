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
import com.ort.tp_ort_tp3_app_gestordenotas.adapters.ViewPagerAdapter
import com.ort.tp_ort_tp3_app_gestordenotas.databinding.ActivityEstudianteBinding
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
    private lateinit var factory: Factory;
    private lateinit var idUsuario: String;
    private lateinit var binding: ActivityEstudianteBinding

    fun getIdUsuario(): String {
        return this.idUsuario;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estudiante);

        //Factory
        this.factory = Factory();

        //View
        this.v = findViewById(android.R.id.content);

        //Navigation bottom bar
        this.navHostFragment = supportFragmentManager.findFragmentById(R.id.estudiante_navgraph) as NavHostFragment;
        this.bottomNavView = findViewById(R.id.bottom_bar);
        NavigationUI.setupWithNavController(this.bottomNavView, this.navHostFragment.navController);


    }

    override fun onStart() {
        super.onStart();
        var idUsuario: String = intent.getStringExtra("idUsuario") as String;
        this.idUsuario = idUsuario;
    }

}















