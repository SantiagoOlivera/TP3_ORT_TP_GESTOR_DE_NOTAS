package com.ort.tp_ort_tp3_app_gestordenotas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class EstudianteActivity : AppCompatActivity() {

    private lateinit var bottomNavView: BottomNavigationView
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estudiante)


        this.navHostFragment = supportFragmentManager.findFragmentById(R.id.estudiante_navgraph) as NavHostFragment;
        this.bottomNavView = findViewById(R.id.bottom_bar);

        NavigationUI.setupWithNavController(this.bottomNavView, this.navHostFragment.navController);

    }
}