package com.ort.tp_ort_tp3_app_gestordenotas.entities

import java.sql.Blob

class ArchivoMateria {

    private lateinit var id: String
    private lateinit var nombre: String
    private lateinit var blob: Blob

    constructor(
        id: String,
        nombre: String,
        blob: Blob
    ){
        this.setID(id);
        this.setNombre(nombre);
        this.setBlob(blob);
    }

    private fun setID(id: String){
        this.id = id;
    }
    private fun setNombre(nombre: String){
        this.nombre = nombre;
    }
    private fun setBlob(blob: Blob){
        this.blob = blob;
    }
}