package com.ort.tp_ort_tp3_app_gestordenotas.fragments.estudiantemateria


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ort.tp_ort_tp3_app_gestordenotas.R
import com.ort.tp_ort_tp3_app_gestordenotas.entities.EstudianteMateria

class EstudianteMateriaFragment : Fragment() {

    lateinit var txtNombreMateria: TextView
    private lateinit var v: View;

    companion object {
        fun newInstance() = EstudianteMateriaFragment()
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        this.v = inflater.inflate(R.layout.fragment_estudiante_materia, container, false)

        this.txtNombreMateria = this.v.findViewById(R.id.txtNombreMateria);

        return this.v;
    }

    override fun onStart() {
        super.onStart();
        val em: EstudianteMateria = EstudianteMateriaFragmentArgs.fromBundle(requireArguments()).estudianteMateria;
        this.txtNombreMateria.text = "Materia seleccionada: ${ em.getMateria().getNombre() } ";

    }



}