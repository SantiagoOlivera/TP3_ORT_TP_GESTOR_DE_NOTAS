package com.ort.tp_ort_tp3_app_gestordenotas.fragments.estudiantes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ort.tp_ort_tp3_app_gestordenotas.R
import com.ort.tp_ort_tp3_app_gestordenotas.adapters.ViewPagerAdapter
import com.ort.tp_ort_tp3_app_gestordenotas.entities.AnioMateria
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Estudiante
import com.ort.tp_ort_tp3_app_gestordenotas.entities.EstudianteMateria
import com.ort.tp_ort_tp3_app_gestordenotas.fragments.estudiantemateria.EstudianteMateriaFragmentArgs

class EstudianteFragment : Fragment() {

    private lateinit var v: View;
    private lateinit var txtNombreEstudiante: TextView;

    private lateinit var adapter: ViewPagerAdapter;
    private lateinit var tabData: ArrayList<String>;
    private lateinit var tabTitle: ArrayList<String>;
    private lateinit var estudiante: Estudiante;

    companion object {
        fun newInstance() = EstudianteFragment()
    }

    private lateinit var viewModel: EstudianteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        this.v = inflater.inflate(R.layout.fragment_estudiante, container, false);
        this.txtNombreEstudiante = this.v.findViewById(R.id.txtNombreCompleto);


        return this.v;
    }

    override fun onStart() {
        super.onStart();
        //Obtiene estudiante muestra informacion
        this.initEstudianteData();
        //Inicia tabs materias
        this.initTabs();
    }

    private fun initEstudianteData() {
        this.estudiante = EstudianteFragmentArgs.fromBundle(requireArguments()).estudiante;
        this.txtNombreEstudiante.text = "Estudiante seleccionado: ${ estudiante.getPersona().getNombreCompleto() } ";
    }

    private fun initTabs() {

        var tabsLayout: TabLayout? = this.v?.findViewById(R.id.tabsLayout);
        var viewPager: ViewPager2? = this.v?.findViewById(R.id.viewPager);


        //Inicia tabs data parametro para filtrar lista y titulo
        this.tabTitle = ArrayList<String>();
        this.tabData = ArrayList<String>();
        this.tabTitle.add("Inscripto");
        this.tabData.add("-1");
        for(am in AnioMateria.entries){
            this.tabTitle.add(am.getText());
            this.tabData.add(am.ordinal.toString());
        }

        this.adapter = ViewPagerAdapter(this, this.tabData, this.estudiante);

        if (viewPager != null) {
            viewPager.adapter = this.adapter;
        };

        if (tabsLayout != null && viewPager != null) {
            TabLayoutMediator(tabsLayout, viewPager, { tab, position ->
                tab.text = this.tabTitle.get(position);
            }).attach();
        };
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EstudianteViewModel::class.java)
        // TODO: Use the ViewModel
    }

}