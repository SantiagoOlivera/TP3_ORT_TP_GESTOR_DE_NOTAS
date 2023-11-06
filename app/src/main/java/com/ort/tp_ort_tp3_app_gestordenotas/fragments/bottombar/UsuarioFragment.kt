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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Timestamp
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
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Usuario
import com.ort.tp_ort_tp3_app_gestordenotas.factories.Factory
import com.ort.tp_ort_tp3_app_gestordenotas.fragments.LoginViewModel
import com.ort.tp_ort_tp3_app_gestordenotas.fragments.estudiantemateria.EstudianteMateriaListFragmentDirections
import com.ort.tp_ort_tp3_app_gestordenotas.fragments.estudiantes.EstudianteListViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.lang.Exception


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
    private lateinit var viewModel: UsuarioViewModel
    private lateinit var carrera: TextView
    private lateinit var sede: TextView
    private lateinit var adapter: UsuarioMateriasAdapter
    private lateinit var listaMaterias: MutableList<EstudianteMateria>
    private lateinit var personaAux: Persona
    private lateinit var factory: Factory
    private lateinit var e: Estudiante

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

    override fun onStart() {
        super.onStart()
        this.carrera.text = "ASC"
        this.sede.text = "Almagro"
        factory = Factory();
        val db = Firebase.firestore



        //con este codigo se ve las materias
      /* this.adapter = UsuarioMateriasAdapter(e.getListEstudianteMateria(),
            { i ->
                Snackbar.make(v, "Click", Snackbar.LENGTH_LONG).show();
                val action = EstudianteMateriaListFragmentDirections.actionEstudianteMateriaListFragmentToEstudianteMateriaFragment(e.getListEstudianteMateria()[i]);
                findNavController().navigate(action);
            }
            )
        this.lista.layoutManager = LinearLayoutManager(context)
        this.lista.adapter = adapter*/



        // con este se ve los datos del estudiante
                       db.collection("Personas")
                           .get()
                           .addOnSuccessListener { personas ->
                               for (persona in personas) {
                                   val personaId = persona.id

                                   viewModel.getEstudiante(personaId).observe(viewLifecycleOwner) { estudiante ->
                                       if (estudiante != null) {
                                           var e: Estudiante = estudiante
                                           if (e != null) {
                                               //Encontrar usuario de la base
                                               var document = personas.find { p ->
                                                   p.id == e?.getIdPersona()
                                               }

                                               if (document != null) {
                                                   this.dni.text = document.data.get("dni") as String
                                                   var dniPersona = this.dni.text as String
                                                   this.nombreCompleto.text =
                                                       document.data.get("nombre") as String
                                                   var nombre = this.nombreCompleto.text as String
                                                   var apellido = document.data.get("apellido") as String
                                                   var fechaNacimiento =
                                                       (document.data?.get("fechaDeNacimiento") as Timestamp).toDate()
                                                   if (this.dni != null && this.nombreCompleto != null && apellido != null && fechaNacimiento != null) {
                                                       if (e != null) {
                                                           this.personaAux = Persona(
                                                               e.getIdPersona(),
                                                               dniPersona,
                                                               nombre,
                                                               apellido,
                                                               fechaNacimiento
                                                           )
                                                       }
                                                   }
                                               } else {
                                                   Log.e("Error de firestore", "Persona no encontrada")
                                               }

                                               if (personaAux != null) {
                                                   db.collection("EstudianteMateria")
                                                       .whereEqualTo("idPersona", personaAux.getIdPersona())
                                                       .get()
                                                       .addOnSuccessListener { result ->
                                                           for (estM in result) {

                                                               var idMateria: String =
                                                                   estM.data.get("idMateria") as String;
                                                               var estado: EstadoMateria =
                                                                   EstadoMateria.entries.get(
                                                                       (estM.data.get(
                                                                           "estado"
                                                                       ) as Number).toInt()
                                                                   );
                                                               var nota: Int =
                                                                   (estM.data.get("nota") as Number).toInt();

                                                               Log.d(
                                                                   "Materia",
                                                                   "Id: $idMateria, Estado: $estado, Nota: $nota"
                                                               )

                                                               Snackbar.make(
                                                                   this.v,
                                                                   "PersonaMateria: ${idMateria}",
                                                                   Snackbar.LENGTH_LONG
                                                               ).show();

                                                               db.collection("Materias")
                                                                   .whereEqualTo("id", idMateria)
                                                                   .get()
                                                                   .addOnSuccessListener { materia ->
                                                                       for (m in materia) {

                                                                           var nombre: String =
                                                                               m.data.get("nombre") as String;
                                                                           val nuevaMateria =
                                                                               EstudianteMateria(
                                                                                   nombre,
                                                                                   estado,
                                                                                   nota
                                                                               )
                                                                           this.listaMaterias.add(
                                                                               nuevaMateria
                                                                           )
                                                                       }

                                                                       if (listaMaterias.isNotEmpty()) {
                                                                           this.adapter =
                                                                               UsuarioMateriasAdapter(
                                                                                   listaMaterias
                                                                               ) { i ->
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
                                                                           this.lista.layoutManager =
                                                                               LinearLayoutManager(context)
                                                                           this.lista.adapter = adapter
                                                                       } else {
                                                                           Snackbar.make(
                                                                               v,
                                                                               "No hay datos de materias validos",
                                                                               Snackbar.LENGTH_LONG
                                                                           ).show()
                                                                       }



                                                                       Snackbar.make(
                                                                           this.v,
                                                                           "Materia: ${idMateria}",
                                                                           Snackbar.LENGTH_LONG
                                                                       ).show();
                                                                   }
                                                           }
                                                       }
                                                   db.collection("Usuarios")
                                                       .whereEqualTo("idPersona", personaAux.getIdPersona())
                                                       .get()
                                                       .addOnSuccessListener { result ->
                                                           for (document in result) {
                                                               this.email.text =
                                                                   document.data.get("email") as String
                                                           }
                                                       }
                                               }
                                           } else {
                                               Log.e("Estudiante", "no se encontro al estudiante")
                                           }

                                       }
                                   }
                                       }
                                   }


                           .addOnFailureListener { exception ->
                               Log.e("UsuarioFragment", "Error al obtener las personas", exception)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(UsuarioViewModel::class.java)
    }
}