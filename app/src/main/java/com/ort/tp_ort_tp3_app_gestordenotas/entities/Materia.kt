package com.ort.tp_ort_tp3_app_gestordenotas.entities

class Materia {

    private lateinit var id: String
    private lateinit var nombre: String
    private lateinit var descripcion: String
    private lateinit var anioMateria: AnioMateria
    private lateinit var archivos: MutableList<ArchivoMateria>

    constructor(
        id: String,
        nombre: String,
        descripcion: String,
        anioMateria: AnioMateria
    ){
        this.setID(id);
        this.setNombre(nombre);
        this.setDescripcion(descripcion);
        this.setAnioMateria(anioMateria);
    }

    constructor(
        id: String, nombre: String,
        descripcion: String,
        anioMateria: AnioMateria,
        archivos: MutableList<ArchivoMateria>
    ){
        this.setID(id);
        this.setNombre(nombre);
        this.setDescripcion(descripcion);
        this.setAnioMateria(anioMateria);
        this.setArchivos(archivos);
    }

    private fun setID(id: String){
        this.id = id;
    }

    fun getId(): String{
        return this.id;
    }

    fun getNombre(): String {
        return this.nombre;
    }

    private fun setNombre(nombre: String){
        this.nombre = nombre;
    }

    private fun setDescripcion(descripcion: String){
        this.descripcion = descripcion;
    }

    private fun setAnioMateria(anioMateria: AnioMateria){
        this.anioMateria = anioMateria;
    }

    private fun initArchivos(){
        this.archivos = mutableListOf();
    }

    private fun setArchivos( archivos: MutableList<ArchivoMateria>){
        if(archivos != null){
            this.archivos = archivos;
        }else{
            this.initArchivos();
        }
    }

}