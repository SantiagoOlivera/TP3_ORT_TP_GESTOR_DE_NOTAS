package com.ort.tp_ort_tp3_app_gestordenotas.fragments.estudiantes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.ort.tp_ort_tp3_app_gestordenotas.R
import com.ort.tp_ort_tp3_app_gestordenotas.adapters.EstudianteAdapter
import com.ort.tp_ort_tp3_app_gestordenotas.adapters.EstudianteMateriaAdapter
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Estudiante
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Usuario
import com.ort.tp_ort_tp3_app_gestordenotas.fragments.estudiantemateria.EstudianteMateriaListFragmentDirections
import com.ort.tp_ort_tp3_app_gestordenotas.repositories.UsuariosRepository

class EstudianteListFragment : Fragment() {

    private lateinit var v: View;
    private lateinit var adapter: EstudianteAdapter;
    private lateinit var estudiantes: MutableList<Estudiante>;
    private lateinit var recycler: RecyclerView

    companion object {
        fun newInstance() = EstudianteListFragment()
    }

    private lateinit var viewModel: EstudianteListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.v = inflater.inflate(R.layout.fragment_estudiante_list, container, false);
        this.recycler = v.findViewById(R.id.RecyclerViewEstudianteList);

        this.estudiantes = UsuariosRepository.getEstudiantes();

        return this.v;
    }

    override fun onStart() {
        super.onStart();

        this.adapter = EstudianteAdapter(
            this.estudiantes,
            { i ->
                Snackbar.make(v, "Click", Snackbar.LENGTH_LONG).show();
                val action = EstudianteListFragmentDirections.actionEstudianteListFragmentToEstudianteFragment(this.estudiantes[i]);
                findNavController().navigate(action);
            }
        );

        this.recycler.layoutManager = LinearLayoutManager(context);
        this.recycler.adapter = this.adapter;

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EstudianteListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}