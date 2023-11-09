package com.ort.tp_ort_tp3_app_gestordenotas.fragments.bottombar

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Timestamp
import androidx.lifecycle.Observer
import com.ort.tp_ort_tp3_app_gestordenotas.EstudianteActivity
import com.ort.tp_ort_tp3_app_gestordenotas.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ort.tp_ort_tp3_app_gestordenotas.adapters.UsuarioMateriasAdapter
import com.ort.tp_ort_tp3_app_gestordenotas.entities.AnioMateria
import com.ort.tp_ort_tp3_app_gestordenotas.entities.EstadoMateria
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Estudiante
import com.ort.tp_ort_tp3_app_gestordenotas.entities.EstudianteMateria
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Materia
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Persona
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Usuario
import com.ort.tp_ort_tp3_app_gestordenotas.factories.Factory
import com.ort.tp_ort_tp3_app_gestordenotas.fragments.LoginViewModel
import com.ort.tp_ort_tp3_app_gestordenotas.fragments.estudiantemateria.EstudianteMateriaListFragmentDirections
import com.ort.tp_ort_tp3_app_gestordenotas.fragments.estudiantes.EstudianteListViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date


class UsuarioFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var v: View;
    private lateinit var nombreCompleto: TextView
    private lateinit var email: TextView
    private lateinit var dni: TextView
    private lateinit var lista: RecyclerView
    private lateinit var viewModel: UsuarioViewModel
    private lateinit var carrera: TextView
    private lateinit var sede: TextView
    private lateinit var adapter: UsuarioMateriasAdapter
    private lateinit var listaMaterias: MutableList<EstudianteMateria>
    private lateinit var factory: Factory
    private lateinit var e: Estudiante
    private lateinit var pers: Persona

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_usuario, container, false)
        nombreCompleto = v.findViewById(R.id.NombreUsuario)
        email = v.findViewById(R.id.emailId)
        dni = v.findViewById(R.id.dniId)
        lista = v.findViewById(R.id.materiasPerfil)
        carrera = v.findViewById(R.id.asc)
        sede = v.findViewById(R.id.almagro)
        listaMaterias = mutableListOf()
        return v
    }

    private fun initDataEstudiante(e: Estudiante){
        //this.txtUsuario.text = e?.getUsuario();
        this.email.text = e?.getEmail();
        this.nombreCompleto.text = e?.getPersona()?.getNombreCompleto();
        this.dni.text = e?.getPersona()?.getDNI();
    }

    private fun initMateriasEstudiante(e: Estudiante){
        var listaMaterias: MutableList<EstudianteMateria> = e.getListEstudianteMateriasInscripto()

        if(listaMaterias.isNotEmpty()){
            this.adapter = UsuarioMateriasAdapter(listaMaterias) { i ->
                Snackbar.make(
                    v,
                    "Click",
                    Snackbar.LENGTH_LONG
                )
                    .show();
                if (e != null) {
                    val action =
                        EstudianteMateriaListFragmentDirections.actionEstudianteMateriaListFragmentToEstudianteMateriaFragment(
                            e.getListEstudianteMateria()[i]
                        );
                    findNavController().navigate(
                        action
                    );
                }
            }
        }else {
            Snackbar.make(
                v,
                "No hay datos de materias validos",
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    private fun getEstudiante() {
        viewModel.estudiante.observe(viewLifecycleOwner, Observer { e ->
            this.initDataEstudiante(e);
            this.initMateriasEstudiante(e)
        });
    }

    override fun onStart() {
        super.onStart()
        this.carrera.text = "ASC"
        this.sede.text = "Almagro"
        factory = Factory();
        val localDate = LocalDate.of(2000, 1, 1)
        val date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
        val persInit: Persona = Persona("","","", date)
        this.pers = persInit
        val db = Firebase.firestore

        var estudianteActivity: EstudianteActivity = parentFragment?.activity as EstudianteActivity;
        val idUsuario: String = estudianteActivity.getIdUsuario();
        //Mandamos a buscar la data del estudiante
        viewModel.getUsuario(idUsuario);
        //Observamos la data del estudiante cuando se ejecuta
        this.getEstudiante()

    }


    companion object {
        fun newInstance() = UsuarioFragment()
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UsuarioViewModel::class.java)
        // TODO: Use the ViewModel
    }

    }

