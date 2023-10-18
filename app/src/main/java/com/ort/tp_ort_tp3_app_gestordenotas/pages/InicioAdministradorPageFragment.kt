package com.ort.tp_ort_tp3_app_gestordenotas.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.ort.tp_ort_tp3_app_gestordenotas.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InicioAdministradorPageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InicioAdministradorPageFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var v: View;
    private lateinit var btnVerEstudiantes: Button;


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
        this.v = inflater.inflate(R.layout.fragment_inicio_administrador_page, container, false);
        this.btnVerEstudiantes = this.v.findViewById(R.id.btnVerEstudiantes);

        return this.v;
    }

    override fun onStart() {
        super.onStart();
        this.btnVerEstudiantes.setOnClickListener {
            Snackbar.make(this.v, "Click", Snackbar.LENGTH_LONG).show();
            //val action = InicioAdministradorPageFragmentDirections.actionInicioAdministradorPageFragmentToEstudianteListFragment();
            //findNavController().navigate(action);
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InicioAdministradorPageFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InicioAdministradorPageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}