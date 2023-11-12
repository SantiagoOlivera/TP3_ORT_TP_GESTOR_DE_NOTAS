package com.ort.tp_ort_tp3_app_gestordenotas.fragments.estudiantemateria

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ort.tp_ort_tp3_app_gestordenotas.R
import com.ort.tp_ort_tp3_app_gestordenotas.entities.EstudianteMateria

class ParcialesEstudianteMateriaEditFragment : Fragment() {

    private lateinit var v: View
    private lateinit var em: EstudianteMateria
    private lateinit var btnModificarNota: Button
    private lateinit var inputParcial: EditText
    private lateinit var inputNota: EditText
    private lateinit var txtParcial: TextView
    private lateinit var materia: TextView
    private lateinit var estudiante: TextView

    companion object {
        fun newInstance() = ParcialesEstudianteMateriaEditFragment()
    }

    private lateinit var viewModel: ParcialesEstudianteMateriaEditViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.v = inflater.inflate(R.layout.fragment_parciales_estudiante_materia_edit, container, false)
        this.btnModificarNota = v.findViewById(R.id.btnIdNotas1)
        this.txtParcial = v.findViewById(R.id.parcialId)
        this.materia = v.findViewById(R.id.materiaId)
        this.estudiante = v.findViewById(R.id.estudianteNomId)
        this.inputParcial = v.findViewById(R.id.inputNroParcial)
        this.inputNota = v.findViewById(R.id.notaParcialId)


        return v
    }

    override fun onStart() {
        this.em = EstudianteMateriaFragmentArgs.fromBundle(requireArguments()).estudianteMateria
        this.initData(em)
        super.onStart()
    }

    private fun initData(em: EstudianteMateria){
        this.txtParcial.text = "Ingresar numero 1 o 2 para que parcial modificar"
        this.materia.text = "La materia es: ${em.getMateria()?.getNombre()}"
        this.estudiante.text = "El estudiante es: ${em.getEstudiante()?.getPersona()?.getNombreCompleto()}"
        this.btnModificarNota.setOnClickListener {
            this.guardar()
        }
    }

    private fun guardar(){
        this.btnModificarNota.onEditorAction(EditorInfo.IME_ACTION_DONE);
        var nota: Int = this.inputNota.text.toString().toInt()
        var nroParcial: Int = this.inputParcial.text.toString().toInt()

        this.viewModel.guardarNotaParcial(this.em, nota, nroParcial);
        this.guardado()
    }

    private fun guardado(){
        this.viewModel.finalizoGuardado.observe(viewLifecycleOwner, Observer { v ->
            this.volver();
        })
    }

    private fun volver() {
        findNavController().navigateUp();
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ParcialesEstudianteMateriaEditViewModel::class.java)
        // TODO: Use the ViewModel
    }

}