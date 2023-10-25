package com.ort.tp_ort_tp3_app_gestordenotas.fragments.bottombar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ort.tp_ort_tp3_app_gestordenotas.EstudianteActivity
import com.ort.tp_ort_tp3_app_gestordenotas.R
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Estudiante

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
    private lateinit var txtEmail: TextView;
    private lateinit var txtDNI: TextView;
    private lateinit var txtUsuario: TextView;
    private lateinit var txtNombreCompleto: TextView;

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
        this.v = inflater.inflate(R.layout.fragment_usuario, container, false);

        this.txtEmail = this.v.findViewById(R.id.txtEmail);
        this.txtNombreCompleto = this.v.findViewById(R.id.txtNombreCompleto);
        this.txtUsuario = this.v.findViewById(R.id.txtUsuario);
        this.txtDNI = this.v.findViewById(R.id.txtDNI);


        return this.v;
    }

    override fun onStart() {
        super.onStart();

        val estudianteActivity: EstudianteActivity = parentFragment?.activity as EstudianteActivity;
        val e: Estudiante = estudianteActivity.getEstudiante();

        this.txtUsuario.text = e?.getUsuario();
        this.txtEmail.text = e?.getEmail();
        this.txtNombreCompleto.text = e?.getPersona()?.getNombreCompleto();
        this.txtDNI.text = e?.getPersona()?.getDNI();


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