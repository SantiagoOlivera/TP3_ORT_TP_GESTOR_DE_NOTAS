package com.ort.tp_ort_tp3_app_gestordenotas.fragments.estudiantemateria

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ort.tp_ort_tp3_app_gestordenotas.R
import com.ort.tp_ort_tp3_app_gestordenotas.adapters.EstudianteMateriaAdapter
import com.ort.tp_ort_tp3_app_gestordenotas.adapters.UsuarioMateriasAdapter
import com.ort.tp_ort_tp3_app_gestordenotas.entities.AnioMateria
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Estudiante
import com.ort.tp_ort_tp3_app_gestordenotas.entities.EstudianteMateria
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Usuario
import com.ort.tp_ort_tp3_app_gestordenotas.fragments.bottombar.InicioFragment
import com.ort.tp_ort_tp3_app_gestordenotas.fragments.bottombar.UsuarioFragment
import com.ort.tp_ort_tp3_app_gestordenotas.fragments.bottombar.UsuarioFragmentDirections
import com.ort.tp_ort_tp3_app_gestordenotas.fragments.estudiantes.EstudianteFragment
import com.ort.tp_ort_tp3_app_gestordenotas.fragments.estudiantes.EstudianteFragmentDirections
import com.ort.tp_ort_tp3_app_gestordenotas.fragments.estudiantes.EstudianteListFragment

class EstudianteMateriaListFragment : Fragment() {

    companion object {
        fun newInstance(e: Estudiante) = EstudianteMateriaListFragment()
    }

    private lateinit var v: View;
    private lateinit var viewModel: EstudianteMateriaListViewModel
    private lateinit var adapter: RecyclerView.Adapter<*>;
    private lateinit var usuario: Usuario;
    private lateinit var recycler: RecyclerView
    private lateinit var estudiante: Estudiante


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        this.v = inflater.inflate(R.layout.fragment_estudiante_materia_list, container, false);
        this.recycler = v.findViewById(R.id.RecyclerViewEstudianteMateriaList);

        return this.v;

    }

    override fun onStart() {
        super.onStart();
        val bundle = arguments;

        //Obtenemos key ordinal tab seleccionada
        var am: Int = arguments?.get("key").toString().toInt();
        var e: Estudiante = arguments?.get("estudiante") as Estudiante;

        //Convertimos el ordinal al año materia

        var listEstudianteMateria: MutableList<EstudianteMateria> = mutableListOf();

        if(am >= 0){
            var anioMateria: AnioMateria = AnioMateria.entries.get(am);
            listEstudianteMateria = e.getListMateriaPorAnio(anioMateria);
        } else if(am === -1){
            listEstudianteMateria = e.getListEstudianteMateriasInscripto();
        }


        /*this.adapter = UsuarioMateriasAdapter(
            listEstudianteMateria
        ) { i ->
            //Snackbar.make(v, "Click Estudiante Materia", Snackbar.LENGTH_LONG).show();
            val em: EstudianteMateria = listEstudianteMateria[i];

            var action: NavDirections? = null;
            if(parentFragment is EstudianteFragment){
                action = EstudianteFragmentDirections.actionEstudianteFragmentToEstudianteMateriaFragment(em);
            }else if(parentFragment is TabsEstudianteMateriaListFragment){
                action = TabsEstudianteMateriaListFragmentDirections.actionTabsEstudianteMateriaListFragmentToEstudianteMateriaFragment(em);
            } else if(parentFragment is UsuarioFragment){
                action = UsuarioFragmentDirections.actionUsuarioFragmentToEstudianteMateriaFragment(em);
            }


            if (action != null) {
                findNavController().navigate(action)
            };
        };

        this.recycler.layoutManager = LinearLayoutManager(context);
        this.recycler.adapter = this.adapter;*/

        this.adapter = when (parentFragment) {
            is EstudianteFragment -> {
                UsuarioMateriasAdapter(listEstudianteMateria) {i ->
                    val em: EstudianteMateria = listEstudianteMateria[i]
                    val action = EstudianteFragmentDirections.actionEstudianteFragmentToEstudianteMateriaFragment(em);
                    findNavController().navigate(action)
                }
            }

            is TabsEstudianteMateriaListFragment -> {
                EstudianteMateriaAdapter(listEstudianteMateria) {i ->
                    val em: EstudianteMateria = listEstudianteMateria[i]
                    val action = TabsEstudianteMateriaListFragmentDirections.actionTabsEstudianteMateriaListFragmentToEstudianteMateriaFragment(em);
                    findNavController().navigate(action)
                }
            }

            is UsuarioFragment -> {
                EstudianteMateriaAdapter(listEstudianteMateria) {i ->
                    val em: EstudianteMateria = listEstudianteMateria[i]
                    val action = UsuarioFragmentDirections.actionUsuarioFragmentToEstudianteMateriaFragment(em);
                    findNavController().navigate(action)
                }
            }
            is InicioFragment -> {
                EstudianteMateriaAdapter(listEstudianteMateria) {i ->
                    //val em: EstudianteMateria = listEstudianteMateria[i]
                    //val action = UsuarioFragmentDirections.actionUsuarioFragmentToEstudianteMateriaFragment(em);
                    //findNavController().navigate(action)
                }
            }
            else -> {
                UsuarioMateriasAdapter(listEstudianteMateria) {i ->
                    val em: EstudianteMateria = listEstudianteMateria[i]
                    val action = UsuarioFragmentDirections.actionUsuarioFragmentToEstudianteMateriaFragment(em);
                    findNavController().navigate(action)
                }
            }
        }
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