package com.ort.tp_ort_tp3_app_gestordenotas.fragments.bottombar

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Estudiante
import com.ort.tp_ort_tp3_app_gestordenotas.entities.EstudianteMateria
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Persona
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Usuario
import com.ort.tp_ort_tp3_app_gestordenotas.factories.Factory
import kotlinx.coroutines.launch

class UsuarioViewModel: ViewModel(){


    var factory: Factory = Factory()
    private lateinit var estudiante: Estudiante
    private val estudianteLiveData = MutableLiveData<Estudiante>()

     fun getEstudiante(id: String): LiveData<Estudiante>{

        viewModelScope.launch {
            try {
                val estudianteResult = factory.getUsuario(id) as? Estudiante
                if(estudianteResult != null) {
                    estudiante = estudianteResult
                    estudianteLiveData.postValue(estudiante)
                }else {
                    Log.e("UsuarioViewModel", "estudianteResult es null")
                }
            }catch (e: Exception){
                Log.e("UsuarioViewModel", "error al obtener el estudiante", e)
            }


        }
        return estudianteLiveData
    }
}