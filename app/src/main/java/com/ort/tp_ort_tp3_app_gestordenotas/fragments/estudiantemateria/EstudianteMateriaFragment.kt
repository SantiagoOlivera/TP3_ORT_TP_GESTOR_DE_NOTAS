

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ort.tp_ort_tp3_app_gestordenotas.R
import com.ort.tp_ort_tp3_app_gestordenotas.adapters.EstudianteMateriaAdapter
import com.ort.tp_ort_tp3_app_gestordenotas.entities.EstudianteMateria

class EstudianteMateriaFragment : Fragment() {

    companion object {
        fun newInstance() = EstudianteMateriaFragment()
    }

    private lateinit var v: View;



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        this.v = inflater.inflate(R.layout.fragment_estudiante_materia, container, false)


        return this.v;
    }



}