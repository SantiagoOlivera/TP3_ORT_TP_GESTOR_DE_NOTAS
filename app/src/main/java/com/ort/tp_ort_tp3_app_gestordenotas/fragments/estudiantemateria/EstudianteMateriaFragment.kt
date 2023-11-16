package com.ort.tp_ort_tp3_app_gestordenotas.fragments.estudiantemateria


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.ort.tp_ort_tp3_app_gestordenotas.R
import com.ort.tp_ort_tp3_app_gestordenotas.entities.EstudianteMateria

class EstudianteMateriaFragment : Fragment() {

    private lateinit var v: View;
    private lateinit var txtNombreMateria: TextView
    private lateinit var btnModificarNotaParcial: Button;
    private lateinit var txtModificar: TextView
    private lateinit var em: EstudianteMateria
    private lateinit var btnModificar: Button;
    private lateinit var txtModificarEm: TextView


    companion object {
        fun newInstance() = EstudianteMateriaFragment()
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        this.v = inflater.inflate(R.layout.fragment_estudiante_materia, container, false)
        this.txtNombreMateria = this.v.findViewById(R.id.txtNombreMateria);
        this.btnModificarNotaParcial = this.v.findViewById(R.id.btnId)
        this.txtModificar = this.v.findViewById(R.id.txtModificar)
        this.btnModificar = this.v.findViewById(R.id.btnModificar);
        this.txtModificarEm = this.v.findViewById(R.id.modificarEm)


        return this.v;
    }

    override fun onStart() {
        super.onStart();
        this.em = EstudianteMateriaFragmentArgs.fromBundle(requireArguments()).estudianteMateria;

        this.txtNombreMateria.text = "Materia seleccionada: ${em.getMateria()?.getNombre()}, Estudiante: ${em.getEstudiante()?.getPersona()?.getNombreCompleto()}, Nota: ${em.getNota()}";
        this.txtModificar.text = "Modificar notas de parciales"
        this.txtModificarEm.text = "Modificar nota final"


        this.btnModificar.setOnClickListener {
            var action =
                EstudianteMateriaFragmentDirections.actionEstudianteMateriaFragmentToEstudianteMateriaEditFragment(this.em);
            findNavController().navigate(action);

        }

        this.btnModificarNotaParcial.setOnClickListener {
            var action =
                EstudianteMateriaFragmentDirections.actionEstudianteMateriaFragmentToParcialesEstudianteMateriaEditFragment(this.em);
            findNavController().navigate(action)


        }

    }

}