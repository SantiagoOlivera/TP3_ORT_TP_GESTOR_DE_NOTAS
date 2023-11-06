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

class UsuarioFragment : Fragment() {

    private lateinit var v: View
    private lateinit var txtEmail: TextView;
    private lateinit var txtDNI: TextView;
    private lateinit var txtUsuario: TextView;
    private lateinit var txtNombreCompleto: TextView;

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
            this.initDataEstudiante(e);
        });
    }

    private fun initDataEstudiante(e: Estudiante){
        this.txtUsuario.text = e?.getUsuario();
        this.txtEmail.text = e?.getEmail();
        this.txtNombreCompleto.text = e?.getPersona()?.getNombreCompleto();
        this.txtDNI.text = e?.getPersona()?.getDNI();
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UsuarioViewModel::class.java)
        // TODO: Use the ViewModel
    }

}