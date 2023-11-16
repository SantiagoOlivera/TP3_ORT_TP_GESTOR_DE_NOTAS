package com.ort.tp_ort_tp3_app_gestordenotas

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ort.tp_ort_tp3_app_gestordenotas.factories.Factory



class EstudianteActivity : AppCompatActivity() {

    private lateinit var bottomNavView: BottomNavigationView
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var v: View
    private lateinit var factory: Factory;
    private lateinit var idUsuario: String;




    fun getIdUsuario(): String {
        return this.idUsuario;
    }

    override fun onStart() {
        super.onStart();
    }

    private fun setIdUsuario() {
        var idUsuario: String = intent.getStringExtra("idUsuario") as String;
        this.idUsuario = idUsuario;
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estudiante);

        //Factory
        this.factory = Factory();

        //View
        this.v = findViewById(android.R.id.content);
        this.navHostFragment =
            supportFragmentManager.findFragmentById(R.id.estudiante_navgraph) as NavHostFragment;

        //Navigation bottom bar
        this.navHostFragment = supportFragmentManager.findFragmentById(R.id.estudiante_navgraph) as NavHostFragment;
        this.bottomNavView = findViewById(R.id.bottom_bar);
        NavigationUI.setupWithNavController(this.bottomNavView, this.navHostFragment.navController);


        this.setIdUsuario();
    }



}


















