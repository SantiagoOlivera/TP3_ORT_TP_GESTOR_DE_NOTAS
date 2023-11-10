package com.ort.tp_ort_tp3_app_gestordenotas.entities

enum class AnioMateria {

    PRIMER_ANIO_PRIMER_CUATRIMESTRE("1° 1°"),
    PRIMER_ANIO_SEGUNDO_CUATRIMESTRE("1° 2°"),
    SEGUNDO_ANIO_PRIMER_CUATRIMESTRE("2° 1°"),
    SEGUNDO_ANIO_SEGUNDO_CUATRIMESTRE("2° 2°")
    ;



    private lateinit var text: String;

    constructor(text: String){
        this.setText(text);
    }

    private fun setText(text: String){
        this.text = text;
    }
    fun getText(): String {
        return this.text;
    }

}