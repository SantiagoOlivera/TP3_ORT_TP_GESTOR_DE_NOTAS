package com.ort.tp_ort_tp3_app_gestordenotas.entities

class Parcial {

    private  var nota: Int = 0

    constructor(nota: Int){
        setNota(nota)
    }

    fun getNota(): Int{
        return this.nota
    }

    private fun setNota(nota: Int){
        this.nota = nota
    }

}