package com.ort.tp_ort_tp3_app_gestordenotas.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ort.tp_ort_tp3_app_gestordenotas.R
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Usuario

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var v: View
    private lateinit var viewModel: MainViewModel
    private lateinit var txtWelcome: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        this.v = inflater.inflate(R.layout.fragment_main, container, false);
        this.txtWelcome = this.v.findViewById(R.id.txtWelcome);

        return this.v;
    }

    override fun onStart() {
        super.onStart();
        val u: Usuario = MainFragmentArgs.fromBundle(requireArguments()).usuario;

        this.txtWelcome.text = "Welcome: ${ u.getPersona().getNombreCompleto() } ";

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}