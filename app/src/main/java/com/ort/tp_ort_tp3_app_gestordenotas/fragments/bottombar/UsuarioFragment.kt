package com.ort.tp_ort_tp3_app_gestordenotas.fragments.bottombar

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ort.tp_ort_tp3_app_gestordenotas.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ort.tp_ort_tp3_app_gestordenotas.adapters.UsuarioMateriasAdapter
import com.ort.tp_ort_tp3_app_gestordenotas.entities.EstudianteMateria


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
        return v
    }

    override fun onStart() {
        super.onStart()
        val db = Firebase.firestore
        val nombreUsuario = arguments?.getString("nombreUsuarioOEmail")
        val alumnosCollection = db.collection("alumnos")

        alumnosCollection.whereEqualTo("usuario", nombreUsuario)
            .get()
            .addOnSuccessListener { result ->
                for (document in result){
                    if(result.isEmpty){
                        Log.e("Error de Firestore", "no se pudo acceder a la base de datos")
                    }else {
                        val id = document.id
                        val nombreCompleto = document.getString("usuario").toString()
                        this.nombreCompleto.text = nombreCompleto
                        val dni = document.getString("dni").toString()
                        this.dni.text = dni
                        val email = document.getString("email").toString()
                        this.email.text = email
                        val materias = document.get("materiasInscriptas") as MutableList<EstudianteMateria>
                        val adapter = UsuarioMateriasAdapter(materias)
                        lista.adapter = adapter
                        lista.layoutManager = LinearLayoutManager(context)
                        adapter.actualizarDatos(materias)


                    }
                }
            }
            .addOnFailureListener{exception->
                Log.e("Error de Firestore", "no se puedo acceder a la base de datos")
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