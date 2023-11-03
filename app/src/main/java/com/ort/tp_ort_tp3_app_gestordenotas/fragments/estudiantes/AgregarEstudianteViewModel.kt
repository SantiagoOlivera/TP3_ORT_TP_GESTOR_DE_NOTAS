package com.ort.tp_ort_tp3_app_gestordenotas.fragments.estudiantes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ort.tp_ort_tp3_app_gestordenotas.entities.Estudiante
import com.ort.tp_ort_tp3_app_gestordenotas.factories.Factory
import kotlinx.coroutines.launch

class AgregarEstudianteViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    val finalizoGuardado: MutableLiveData<Boolean> = MutableLiveData<Boolean>();
    fun guardarEstudiante(e: Estudiante){
        this.viewModelScope.launch {
            var factory: Factory = Factory();
            factory.setEstudiante(e);
            finalizoGuardado.value = true;
        }
    }


}