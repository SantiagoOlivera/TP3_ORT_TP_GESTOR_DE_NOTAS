package com.ort.tp_ort_tp3_app_gestordenotas

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.ActionMode
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.ort.tp_ort_tp3_app_gestordenotas.fragments.LoginFragmentDirections

class AdministradorActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    private lateinit var navView: NavigationView
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);





        val toolbar: Toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        this.drawer = findViewById(R.id.drawer_layout);
        this.toggle = ActionBarDrawerToggle(this, this.drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        this.drawer.addDrawerListener(this.toggle);
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setHomeButtonEnabled(true);
        //Menu icon
        supportActionBar?.setHomeAsUpIndicator(R.drawable.burger_menu_icon);

        val navigationView: NavigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        this.navHostFragment = supportFragmentManager.findFragmentById(R.id.administrador_navgraph) as NavHostFragment;
        this.navView = findViewById(R.id.nav_view);
        NavigationUI.setupWithNavController(this.navView, this.navHostFragment.navController);


    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.estudianteListFragment -> {
                Toast.makeText(this, "Click inicio", Toast.LENGTH_SHORT).show();
            }
            R.id.usuarioFragment -> {
                Toast.makeText(this, "Click usuario", Toast.LENGTH_SHORT).show();
            }
        }

        this.drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState);
        this.toggle.syncState();

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig);
        this.toggle.onConfigurationChanged(newConfig);

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(this.toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}