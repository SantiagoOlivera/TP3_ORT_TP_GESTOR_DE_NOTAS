package com.ort.tp_ort_tp3_app_gestordenotas.fragments.estudiantes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.ort.tp_ort_tp3_app_gestordenotas.R
import com.ort.tp_ort_tp3_app_gestordenotas.adapters.EstudianteAdapter
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Administrador
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Estudiante
import com.ort.tp_ort_tp3_app_gestordenotas.factories.Factory
import com.ort.tp_ort_tp3_app_gestordenotas.repositories.UsuariosRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class EstudianteListFragment : Fragment() {

    private lateinit var v: View;
    private lateinit var adapter: EstudianteAdapter;
    private lateinit var estudiantes: MutableList<Estudiante>;
    private lateinit var recycler: RecyclerView
    private lateinit var btnAgregarEstudiante: Button


    private var factory: Factory = Factory();


    companion object {
        fun newInstance() = EstudianteListFragment()
    }

    private lateinit var viewModel: EstudianteListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        this.v = inflater.inflate(R.layout.fragment_estudiante_list, container, false);
        this.recycler = this.v.findViewById(R.id.RecyclerViewEstudiantesList);
        this.estudiantes = mutableListOf();

        //this.btnAgregarEstudiante = this.v.findViewById(R.id.btnAgregarEstudiante) as Button;

        return this.v;
    }

    override fun onStart() {
        super.onStart();

        this.getEstudiantesList();

    }

    private fun getEstudiantesList(){

        this.estudiantes = mutableListOf();

        val parentJob = Job();
        val scope: CoroutineScope = CoroutineScope(Dispatchers.Default + parentJob);
        scope.launch {
            estudiantes = factory.getEstudiantes();
            Snackbar.make(v, "Est: ${estudiantes[0]?.getPersona()?.getNombreCompleto()}", Snackbar.LENGTH_LONG).show();
            initListEstudiantes(estudiantes);
        }

        //estudiantes.add(UsuariosRepository.getUsuarios().get(0) as Estudiante);
        //initListEstudiantes(estudiantes);


    }

    private fun initListEstudiantes(list: MutableList<Estudiante>){
        if(list != null){
            this.adapter = EstudianteAdapter(
                list,
                { i ->
                    Snackbar.make(v, "Click", Snackbar.LENGTH_LONG).show();
                    val action = EstudianteListFragmentDirections.actionEstudianteListFragmentToEstudianteFragment(list[i]);
                    findNavController().navigate(action);
                })

            //Snackbar.make(v, "Est: ${estudiantes[0]?.getPersona()?.getNombreCompleto()}", Snackbar.LENGTH_LONG).show();
            this.recycler.layoutManager = LinearLayoutManager(context);
            this.recycler.adapter = this.adapter;
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EstudianteListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}