package com.ort.tp_ort_tp3_app_gestordenotas.fragments.estudiantemateria

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ort.tp_ort_tp3_app_gestordenotas.entities.EstudianteMateria
import com.ort.tp_ort_tp3_app_gestordenotas.factories.Factory
import kotlinx.coroutines.launch

class EstudianteMateriaEditViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    val finalizoGuardado: MutableLiveData<Boolean> = MutableLiveData<Boolean>();
    fun guardarEstudianteMateria(em: EstudianteMateria){

        viewModelScope.launch {
            var factory: Factory = Factory();
            factory.setEstudianteMateria(em);
            finalizoGuardado.value = true;
        }
    }

}