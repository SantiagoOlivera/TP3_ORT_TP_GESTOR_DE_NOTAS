package com.ort.tp_ort_tp3_app_gestordenotas.fragments.estudiantemateria

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
import com.ort.tp_ort_tp3_app_gestordenotas.EstudianteActivity
import com.ort.tp_ort_tp3_app_gestordenotas.R
import com.ort.tp_ort_tp3_app_gestordenotas.adapters.EstudianteMateriaAdapter
import com.ort.tp_ort_tp3_app_gestordenotas.entities.AnioMateria
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Estudiante
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Usuario
import com.ort.tp_ort_tp3_app_gestordenotas.repositories.UsuariosRepository

class EstudianteMateriaListFragment : Fragment() {

    companion object {
        fun newInstance() = EstudianteMateriaListFragment()
    }

    private lateinit var v: View;
    private lateinit var viewModel: EstudianteMateriaListViewModel
    private lateinit var adapter: EstudianteMateriaAdapter;
    private lateinit var usuario: Usuario;
    private lateinit var recycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        this.v = inflater.inflate(R.layout.fragment_estudiante_materia_list, container, false);
        this.recycler = v.findViewById(R.id.RecyclerViewEstudianteMateriaList);

        var usuarios: List<Usuario> = UsuariosRepository.getUsuarios();
        //val estudianteActivity: EstudianteActivity = parentFragment?.activity as EstudianteActivity;
        //val e: Estudiante = estudianteActivity.getEstudiante();
        //this.usuario = e;

        this.usuario = usuarios[0] as Estudiante;

        return this.v;

    }

    override fun onStart() {
        super.onStart();
        val bundle = arguments;

        //Obtenemos key ordinal tab seleccionada
        var am: String = arguments?.get("key").toString();

        //Convertimos el ordinal al año materia
        var anioMateria: AnioMateria = AnioMateria.entries.get(am.toInt());

        var e: Estudiante = this.usuario as Estudiante;

        this.adapter = EstudianteMateriaAdapter(
            e.getListMateriaPorAnio(anioMateria),
            { i ->
                Snackbar.make(v, "Click", Snackbar.LENGTH_LONG).show();
                val action = EstudianteMateriaListFragmentDirections.actionEstudianteMateriaListFragmentToEstudianteMateriaFragment(e.getListEstudianteMateria()[i]);
                findNavController().navigate(action);
            }
        );

        this.recycler.layoutManager = LinearLayoutManager(context);
        this.recycler.adapter = this.adapter;

        //textView.text = bundle!!.getString("key");
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EstudianteMateriaListViewModel::class.java)
        // TODO: Use the ViewModel
    }


}