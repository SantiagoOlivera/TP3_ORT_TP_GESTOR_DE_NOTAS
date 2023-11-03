package com.ort.tp_ort_tp3_app_gestordenotas.fragments.estudiantes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Estudiante
import com.ort.tp_ort_tp3_app_gestordenotas.factories.Factory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class EstudianteListViewModel : ViewModel() {
    // TODO: Implement the ViewModel


    val estudiantes: MutableLiveData<MutableList<Estudiante>> = MutableLiveData<MutableList<Estudiante>>()
    fun getEstudiantes() {
        var factory: Factory = Factory();
        viewModelScope.launch {
            estudiantes.value = factory.getEstudiantes();
        }

    }





}