package com.ort.tp_ort_tp3_app_gestordenotas.fragments.bottombar

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.ort.tp_ort_tp3_app_gestordenotas.EstudianteActivity
import com.ort.tp_ort_tp3_app_gestordenotas.R
import com.ort.tp_ort_tp3_app_gestordenotas.adapters.EstudianteMateriaAdapter
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Estudiante
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Usuario

class EstudianteMateriaInscriptoFragment : Fragment() {

    companion object {
        fun newInstance() = EstudianteMateriaInscriptoFragment()
    }

    private lateinit var viewModel: EstudianteMateriaInscriptoViewModel;
    private lateinit var v: View;
    private lateinit var adapter: EstudianteMateriaAdapter;
    private lateinit var recycler: RecyclerView;
    private lateinit var usuario: Usuario;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.v = inflater.inflate(R.layout.fragment_estudiante_materia_inscripto, container, false)
        //this.recycler = v.findViewById(R.id.RecyclerViewEstudianteMateriasInscriptoList);

        val estudianteActivity: EstudianteActivity = parentFragment?.activity as EstudianteActivity;
        val e: Estudiante = estudianteActivity.getEstudiante();
        this.usuario = e;

        return this.v;
    }

    override fun onStart() {
        super.onStart();

        var e: Estudiante = this.usuario as Estudiante;

        this.adapter = EstudianteMateriaAdapter(
            e.getListEstudianteMateriasInscripto(),
            { i ->
                Snackbar.make(v, "Click", Snackbar.LENGTH_LONG).show();
                //val action = EstudianteMateriaListFragmentDirections.actionEstudianteMateriaListFragmentToEstudianteMateriaFragment(e.getListEstudianteMateria()[i]);
                //findNavController().navigate(action);
            }
        );

        this.recycler.layoutManager = LinearLayoutManager(context);
        this.recycler.adapter = this.adapter;

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EstudianteMateriaInscriptoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}