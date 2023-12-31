package com.ort.tp_ort_tp3_app_gestordenotas.fragments.estudiantes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Timestamp
import com.ort.tp_ort_tp3_app_gestordenotas.R
import com.ort.tp_ort_tp3_app_gestordenotas.entities.EstadoMateria
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Estudiante
import com.ort.tp_ort_tp3_app_gestordenotas.entities.EstudianteMateria
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Materia
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Persona
import com.ort.tp_ort_tp3_app_gestordenotas.factories.Factory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.Date

class AgregarEstudianteFragment : Fragment() {

    companion object {
        fun newInstance() = AgregarEstudianteFragment()
    }

    private lateinit var viewModel: AgregarEstudianteViewModel
    private lateinit var v: View
    private var factory: Factory = Factory();

    private lateinit var btnAgregarEstudiante: Button


    private lateinit var inputDNI: EditText
    private lateinit var inputApellido: EditText
    private lateinit var inputNombre: EditText
    private lateinit var inputPassword: EditText
    private lateinit var inputFechaDeNacimiento: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.v = inflater.inflate(R.layout.fragment_agregar_estudiante, container, false);

        this.btnAgregarEstudiante = this.v.findViewById(R.id.btnAgregarEstudiante);

        this.inputDNI = this.v.findViewById(R.id.inputDNI);
        this.inputApellido = this.v.findViewById(R.id.inputApellido);
        this.inputNombre = this.v.findViewById(R.id.inputNombre);
        this.inputPassword = this.v.findViewById(R.id.inputPassword);
        this.inputFechaDeNacimiento = this.v.findViewById(R.id.inputFechaDeNacimiento);

        return this.v;
    }

    override fun onStart() {
        super.onStart();

        this.btnAgregarEstudiante.setOnClickListener {
            var e: Estudiante = getEstudiante();
            this.viewModel.guardarEstudiante(e);
            this.guardarEstudiante();
        }
    }

    private fun guardarEstudiante(){
        this.viewModel.finalizoGuardado.observe(viewLifecycleOwner, Observer { v ->
            volverMenuPrincipal();
        });
    }

    private fun volverMenuPrincipal(){
        findNavController().navigateUp();
        Snackbar.make(this.v, "Se agrego estudiante", Snackbar.LENGTH_LONG).show();
    }

    private fun getEstudiante(): Estudiante {

        var dni: String = this.inputDNI.text.toString().trim();
        var apellido: String = this.inputApellido.text.toString().trim();
        var nombre: String = this.inputNombre.text.toString().trim();
        var password: String = this.inputPassword.text.toString().trim();
        var fechaDeNacimiento: String = this.inputFechaDeNacimiento.text.toString().trim();

        var splitfn: List<String> = fechaDeNacimiento.split("/");
        var dia: Int = splitfn[0].toInt();
        var mes: Int = splitfn[1].toInt() - 1;
        var anio: Int = splitfn[2].toInt() - 1900;

        var fn: Date = Date(anio, mes, dia);

        var p: Persona = Persona(dni, nombre, apellido, fn);
        var email: String = "${nombre.replace(" ", "").toLowerCase()}.${apellido.replace(" ", "").toLowerCase()}@ort.edu.ar";

        var e: Estudiante = Estudiante(dni, email, password, p);

        return e;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AgregarEstudianteViewModel::class.java)
        // TODO: Use the ViewModel
    }

}