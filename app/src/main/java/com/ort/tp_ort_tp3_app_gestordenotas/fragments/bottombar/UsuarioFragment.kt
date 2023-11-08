package com.ort.tp_ort_tp3_app_gestordenotas.fragments.bottombar

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ort.tp_ort_tp3_app_gestordenotas.EstudianteActivity
import com.ort.tp_ort_tp3_app_gestordenotas.R
import com.ort.tp_ort_tp3_app_gestordenotas.adapters.ViewPagerAdapter
import com.ort.tp_ort_tp3_app_gestordenotas.entities.AnioMateria
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Estudiante

class UsuarioFragment : Fragment() {

    private lateinit var v: View
    private lateinit var txtEmail: TextView;
    private lateinit var txtDNI: TextView;
    private lateinit var txtUsuario: TextView;
    private lateinit var txtNombreCompleto: TextView;

    private lateinit var adapter: ViewPagerAdapter;
    private lateinit var tabData: ArrayList<String>;
    private lateinit var tabTitle: ArrayList<String>;
    private lateinit var estudiante: Estudiante;

    companion object {
        fun newInstance() = UsuarioFragment()
    }

    private lateinit var viewModel: UsuarioViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.v = inflater.inflate(R.layout.fragment_usuario, container, false);
        this.txtEmail = this.v.findViewById(R.id.txtEmail);
        this.txtNombreCompleto = this.v.findViewById(R.id.txtNombreCompleto);
        this.txtUsuario = this.v.findViewById(R.id.txtUsuario);
        this.txtDNI = this.v.findViewById(R.id.txtDNI);

        return this.v;
    }

    override fun onStart() {
        super.onStart();
        //Obtener id usuario del activity
        var estudianteActivity: EstudianteActivity = parentFragment?.activity as EstudianteActivity;
        var idUsuario: String = estudianteActivity.getIdUsuario();
        //Mandamos a buscar la data del estudiante
        viewModel.getUsuario(idUsuario);
        //Observamos la data del estudiante cuando se ejecuta
        this.getEstudiante();
    }

    private fun getEstudiante() {
        viewModel.estudiante.observe(viewLifecycleOwner, Observer { e ->
            this.estudiante = e;
            this.initDataEstudiante(e);
            this.initTabs();
        });
    }

    private fun initDataEstudiante(e: Estudiante){
        this.txtUsuario.text = e?.getUsuario();
        this.txtEmail.text = e?.getEmail();
        this.txtNombreCompleto.text = e?.getPersona()?.getNombreCompleto();
        this.txtDNI.text = e?.getPersona()?.getDNI();
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
        viewModel = ViewModelProvider(this).get(UsuarioViewModel::class.java)
        // TODO: Use the ViewModel
    }

}