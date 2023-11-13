package com.ort.tp_ort_tp3_app_gestordenotas.fragments.estudiantemateria

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ort.tp_ort_tp3_app_gestordenotas.entities.EstudianteMateria
import com.ort.tp_ort_tp3_app_gestordenotas.factories.Factory
import kotlinx.coroutines.launch

class ParcialesEstudianteMateriaEditViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    val finalizoGuardado: MutableLiveData<Boolean> = MutableLiveData<Boolean>();
    fun guardarNotaParcial(em: EstudianteMateria, nota: Int, nroParcial: Int){

        viewModelScope.launch {
            var factory: Factory = Factory();
            factory.setNotaParial(em, nota, nroParcial);
            finalizoGuardado.value = true;
        }
    }
}