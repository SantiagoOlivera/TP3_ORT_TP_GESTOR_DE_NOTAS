package com.ort.tp_ort_tp3_app_gestordenotas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class AdministradorActivity : AppCompatActivity() {

    private lateinit var navView: NavigationView
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_administrador);

        //this.navHostFragment = supportFragmentManager.findFragmentById(R.id.administrador_navgraph) as NavHostFragment;
        //this.navView = findViewById(R.id.navigationViewAdministrador);

        //NavigationUI.setupWithNavController(this.navView, this.navHostFragment.navController);

    }
}