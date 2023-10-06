package com.ort.tp_ort_tp3_app_gestordenotas.fragments.pages

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ort.tp_ort_tp3_app_gestordenotas.R

class EstudiantePageFragment : Fragment() {

    companion object {
        fun newInstance() = EstudiantePageFragment()
    }

    private lateinit var viewModel: EstudiantePageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_estudiante_page, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EstudiantePageViewModel::class.java)
        // TODO: Use the ViewModel
    }

}