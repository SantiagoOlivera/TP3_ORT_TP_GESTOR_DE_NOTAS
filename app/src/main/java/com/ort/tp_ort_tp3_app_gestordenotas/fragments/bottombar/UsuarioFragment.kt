package com.ort.tp_ort_tp3_app_gestordenotas.fragments.bottombar

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.ort.tp_ort_tp3_app_gestordenotas.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ort.tp_ort_tp3_app_gestordenotas.EstudianteActivity
import com.ort.tp_ort_tp3_app_gestordenotas.adapters.UsuarioMateriasAdapter
import com.ort.tp_ort_tp3_app_gestordenotas.entities.AnioMateria
import com.ort.tp_ort_tp3_app_gestordenotas.entities.EstadoMateria
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Estudiante
import com.ort.tp_ort_tp3_app_gestordenotas.entities.EstudianteMateria
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Materia
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Persona
import com.ort.tp_ort_tp3_app_gestordenotas.fragments.LoginViewModel


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UsuarioFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UsuarioFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var v: View;
    private lateinit var nombreCompleto: TextView
    private lateinit var email: TextView
    private lateinit var dni: TextView
    private lateinit var lista: RecyclerView
    private lateinit var viewModel: LoginViewModel
    private lateinit var carrera: TextView
    private lateinit var sede: TextView
    private lateinit var adapter: UsuarioMateriasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)
        v = inflater.inflate(R.layout.fragment_usuario, container, false)
        nombreCompleto = v.findViewById(R.id.NombreUsuario)
        email = v.findViewById(R.id.emailId)
        dni = v.findViewById(R.id.dniId)
        lista = v.findViewById(R.id.materiasPerfil)
        carrera = v.findViewById(R.id.asc)
        sede = v.findViewById(R.id.almagro)
        adapter = UsuarioMateriasAdapter(mutableListOf())
        lista.adapter = adapter
        lista.layoutManager = LinearLayoutManager(context)
        return v
    }

    override fun onStart() {
        super.onStart()
        this.carrera.text = "ASC"
        this.sede.text = "Almagro"
        val estudianteActivity: EstudianteActivity = parentFragment?.activity as EstudianteActivity;
        val e: Estudiante = estudianteActivity.getEstudiante();
        val usuario = e
        this.email.text = viewModel.email

        var p: Persona? = null;
        val db = Firebase.firestore

        db.collection("Personas")
            .get()
            .addOnSuccessListener { personas ->


                //Encontrar usuario de la base
                var document = personas.find { p ->
                    p.id == e.getIdPersona()
                }

                if (document != null) {
                    this.dni.text = document.data.get("dni") as String
                    this.nombreCompleto.text = document.data.get("nombre") as String
                } else {
                    Log.e("Error de firestore", "Persona no encontrada")
                }

                if (p != null) {
                    db.collection("EstudianteMateria")
                        .whereEqualTo("idPersona", p.getIdPersona())
                        .get()
                        .addOnSuccessListener { result ->
                            for (document in result) {

                                var idMateria: String = document.get("idMateria") as String;
                                var estado: EstadoMateria =
                                    EstadoMateria.entries.get((document.get("estado") as Number).toInt());
                                var nota: Int = (document.get("nota") as Number).toInt();

                                Snackbar.make(
                                    this.v,
                                    "PersonaMateria: ${idMateria}",
                                    Snackbar.LENGTH_LONG
                                ).show();

                                db.collection("Materias")
                                    .document(idMateria)
                                    .get()
                                    .addOnSuccessListener { materia ->

                                        Snackbar.make(
                                            this.v,
                                            "Materia: ${idMateria}",
                                            Snackbar.LENGTH_LONG
                                        ).show();

                                        var nombre: String = materia.get("nombre") as String;
                                        val listaMaterias: MutableList<EstudianteMateria> = mutableListOf()
                                        val nuevaMateria = EstudianteMateria(nombre, estado, nota)
                                        listaMaterias.add(nuevaMateria)
                                        adapter.actualizarDatos(listaMaterias)

                                    }
                            }
                        }
                }
            }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UsuarioFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UsuarioFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}