package com.ort.tp_ort_tp3_app_gestordenotas.fragments.estudiantes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ort.tp_ort_tp3_app_gestordenotas.R
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Estudiante
import com.ort.tp_ort_tp3_app_gestordenotas.entities.EstudianteMateria
import com.ort.tp_ort_tp3_app_gestordenotas.fragments.estudiantemateria.EstudianteMateriaFragmentArgs

class EstudianteFragment : Fragment() {

    private lateinit var v: View;
    private lateinit var txtNombreEstudiante: TextView;

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
        val e: Estudiante = EstudianteFragmentArgs.fromBundle(requireArguments()).estudiante;
        this.txtNombreEstudiante.text = "Estudiante seleccionado: ${ e.getPersona().getNombreCompleto() } ";
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EstudianteViewModel::class.java)
        // TODO: Use the ViewModel
    }

}