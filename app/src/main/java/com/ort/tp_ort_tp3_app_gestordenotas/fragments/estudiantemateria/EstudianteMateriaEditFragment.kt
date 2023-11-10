package com.ort.tp_ort_tp3_app_gestordenotas.fragments.estudiantemateria

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
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
import com.ort.tp_ort_tp3_app_gestordenotas.factories.Factory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class EstudianteMateriaEditFragment : Fragment() {

    private lateinit var v: View;
    private lateinit var txtNombreMateria: TextView
    private lateinit var txtNombreEstudiante: TextView
    private lateinit var inputNota: EditText
    private lateinit var btnGuardar: Button
    private lateinit var em: EstudianteMateria;

    companion object {
        fun newInstance() = EstudianteMateriaEditFragment()
    }

    private lateinit var viewModel: EstudianteMateriaEditViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.v = inflater.inflate(R.layout.fragment_estudiante_materia_edit, container, false);

        this.txtNombreMateria = this.v.findViewById(R.id.txtNombreMateria);
        this.txtNombreEstudiante = this.v.findViewById(R.id.txtNombreEstudiante);
        this.inputNota = this.v.findViewById(R.id.inputNota);
        this.btnGuardar = this.v.findViewById(R.id.btnGuardar);

        return this.v;
    }

    override fun onStart() {
        super.onStart();
        this.em = EstudianteMateriaFragmentArgs.fromBundle(requireArguments()).estudianteMateria;
        this.initData(em);
    }

    private fun initData(em: EstudianteMateria) {
        this.txtNombreMateria.text = "Materia seleccionada: ${em.getMateria().getNombre()}, Estudiante: ${em.getEstudiante().getPersona().getNombreCompleto()}, Nota: ${em.getNota()}";
        this.txtNombreEstudiante.text = em.getEstudiante().getPersona().getNombreCompleto();
        this.inputNota.setText(em.getNota().toString());
        this.btnGuardar.setOnClickListener{
            this.guardar();
        }
    }

    private fun guardar(){
        this.btnGuardar.onEditorAction(EditorInfo.IME_ACTION_DONE);
        var nota: Int = this.inputNota.text.toString().toInt();
        this.em.setNota(nota);

        this.viewModel.guardarEstudianteMateria(em);
        this.observeGuardado();

    }

    private fun observeGuardado(){
        this.viewModel.finalizoGuardado.observe(viewLifecycleOwner, Observer { v ->
            volver();
        });
    }
    private fun volver(){

        findNavController().navigateUp();
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EstudianteMateriaEditViewModel::class.java)
        // TODO: Use the ViewModel
    }

}