package com.ort.tp_ort_tp3_app_gestordenotas.fragments.bottombar

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ort.tp_ort_tp3_app_gestordenotas.EstudianteActivity
import com.ort.tp_ort_tp3_app_gestordenotas.R
import com.ort.tp_ort_tp3_app_gestordenotas.adapters.ViewPagerAdapter
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Estudiante
import com.ort.tp_ort_tp3_app_gestordenotas.entities.AnioMateria

import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import org.w3c.dom.Text
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream


class InicioFragment : Fragment() {
    private lateinit var estudiante: Estudiante;
    private lateinit var v: View;
    private lateinit var nombreCompleto: TextView;
    private lateinit var materiasInscriptas: TextView;
    private lateinit var tabTitle: ArrayList<String>;
    private lateinit var tabData: ArrayList<String>;
    private lateinit var adapterPager: ViewPagerAdapter;
    private lateinit var progreso: TextView;

    companion object {
        fun newInstance() = InicioFragment()
    }

    private lateinit var viewModel: InicioViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.fragment_inicio, container, false);
        progreso = v.findViewById(R.id.txtMateriasRestantes)
        materiasInscriptas = v.findViewById(R.id.txtMateriasInscriptas)
        nombreCompleto = v.findViewById(R.id.nombreCompleto);

        return v;
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(InicioViewModel::class.java)
        // TODO: Use the ViewModel
    }


    override fun onStart() {
        super.onStart()

        var estudianteActivity: EstudianteActivity = parentFragment?.activity as EstudianteActivity;
        val idUsuario: String = estudianteActivity.getIdUsuario();
        //Mandamos a buscar la data del estudiante
        viewModel.getUsuario(idUsuario);
        //Observamos la data del estudiante cuando se ejecuta
        this.getEstudiante()
    }




    private fun getEstudiante() {
        viewModel.estudiante.observe(viewLifecycleOwner, Observer { e ->
            this.estudiante = e;
            this.initDataEstudiante(e);
            this.initTabs();
        });
    }


    private fun initDataEstudiante(e: Estudiante){
        //this.txtUsuario.text = e?.getUsuario();
        //this.email.text = e?.getEmail();
        this.materiasInscriptas.text = e?.getNumeroMateriasInscriptas().toString();
        this.progreso.text = e?.getProgreso();
        this.nombreCompleto.text = e?.getPersona()?.getNombreCompleto();
        //this.dni.text = e?.getPersona()?.getDNI();
    }

    private fun initTabs() {
        var tabsLayout: TabLayout? = this.v?.findViewById(R.id.tabsLayout);
        var viewPager: ViewPager2? = this.v?.findViewById(R.id.viewPager);

        //Inicia tabs data parametro para filtrar lista y titulo
        this.tabTitle = ArrayList<String>();
        this.tabData = ArrayList<String>();
        //this.tabTitle.add("Inscripto");
        //this.tabData.add("-1");
        for(am in AnioMateria.entries){
            this.tabTitle.add(am.getText());
            this.tabData.add(am.ordinal.toString());
        }

        this.adapterPager = ViewPagerAdapter(this, this.tabData, this.estudiante);

        if (viewPager != null) {
            viewPager.adapter = this.adapterPager;
        };

        if (tabsLayout != null && viewPager != null) {
            TabLayoutMediator(tabsLayout, viewPager, { tab, position ->
                tab.text = this.tabTitle.get(position);
            }).attach();
        };

    }
}