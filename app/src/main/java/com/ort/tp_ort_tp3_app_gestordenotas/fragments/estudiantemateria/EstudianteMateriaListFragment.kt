package com.ort.tp_ort_tp3_app_gestordenotas.fragments.estudiantemateria

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ort.tp_ort_tp3_app_gestordenotas.R
import com.ort.tp_ort_tp3_app_gestordenotas.adapters.EstudianteMateriaAdapter
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
        this.usuario = usuarios[0];

        return this.v;

    }

    override fun onStart() {
        super.onStart();

        var e: Estudiante = this.usuario as Estudiante;

        this.adapter = EstudianteMateriaAdapter( e.getListEstudianteMateria() );
        //{ i ->
        //Snackbar.make(v, "Click en ${ repository.getProducts()[position].getName() }", Snackbar.LENGTH_LONG).show();
        //val action = ProductsListFragmentDirections.actionProductsListFragment2ToProductFragment(this.products[i]);
        //findNavController().navigate(action);
        //}
        this.recycler.layoutManager = LinearLayoutManager(context);
        this.recycler.adapter = this.adapter;

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EstudianteMateriaListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}