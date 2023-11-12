package com.ort.tp_ort_tp3_app_gestordenotas.fragments.bottombar

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.ort.tp_ort_tp3_app_gestordenotas.EstudianteActivity
import com.ort.tp_ort_tp3_app_gestordenotas.R
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Estudiante

class InicioFragment : Fragment() {
    private lateinit var estudiante: Estudiante;
    private lateinit var v: View;
    private  lateinit var nombreCompleto: TextView;

    companion object {
        fun newInstance() = InicioFragment()
    }

    private lateinit var viewModel: InicioViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_inicio, container, false)
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
        });
    }


    private fun initDataEstudiante(e: Estudiante){
        //this.txtUsuario.text = e?.getUsuario();
        //this.email.text = e?.getEmail();
        this.nombreCompleto.text = e?.getPersona()?.getNombreCompleto();
        //this.dni.text = e?.getPersona()?.getDNI();
    }
}