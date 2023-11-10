package com.ort.tp_ort_tp3_app_gestordenotas.fragments.bottombar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ort.tp_ort_tp3_app_gestordenotas.EstudianteActivity
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Estudiante
import com.ort.tp_ort_tp3_app_gestordenotas.factories.Factory
import kotlinx.coroutines.launch

class UsuarioViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    val estudiante: MutableLiveData<Estudiante> = MutableLiveData<Estudiante>()
    val estudianteABuscar: MutableLiveData<Estudiante> = MutableLiveData<Estudiante>()
    fun getUsuario(idUsuario:String) {
        var factory: Factory = Factory();
        viewModelScope.launch {
            estudiante.value = factory.getEstudiante(idUsuario);
        }

    }

     fun getEstudiante(idUsuario: String) {
        var factory: Factory = Factory()

        viewModelScope.launch {
            estudianteABuscar.value = factory.getEstudiante(idUsuario)
        }


    //return factory.getEstudiante(idUsuario)
    }
}