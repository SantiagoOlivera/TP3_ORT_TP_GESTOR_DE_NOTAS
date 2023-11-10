package com.ort.tp_ort_tp3_app_gestordenotas.entities

import android.media.Image
import java.util.Date

class Persona {

    private lateinit var idPersona:String
    private lateinit var dni: String
    private lateinit var nombre: String
    private lateinit var apellido: String
    private lateinit var fechaDeNacimiento: Date
    private lateinit var imagen: Image

    constructor(
        idPersona: String,
        dni: String,
        nombre: String,
        apellido: String,
        fechaDeNacimiento: Date,
    ){
        this.setIdPersona(idPersona);
        this.setDNI(dni);
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setFechaDeNacimiento(fechaDeNacimiento);
    }

    constructor(
        dni: String,
        nombre: String,
        apellido: String,
        fechaDeNacimiento: Date,
    ){
        this.setDNI(dni);
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setFechaDeNacimiento(fechaDeNacimiento);
    }

    fun getNombreCompleto(): String{
        return this.getNombre() + " " + this.getApellido();
    }

    fun getDNI(): String {
        return this.dni;
    }
    fun getNombre():String{
        return this.nombre;
    }
    fun getApellido(): String{
        return this.apellido;
    }
    fun getIdPersona(): String{
        return this.idPersona;
    }

    fun getFechaDeNacimiento(): Date{
        return this.fechaDeNacimiento;
    }

    fun setIdPersona(idPersona: String){
        this.idPersona = idPersona;
    }

    private fun setImagen(imagen: Image){
        this.imagen = imagen;
    }

    private fun setDNI(dni:String){
        this.dni = dni;
    }

    private fun setNombre(nombre:String){
        this.nombre = nombre;
    }

    private fun setApellido(apellido:String){
        this.apellido = apellido;
    }

    private fun setFechaDeNacimiento(fechaDeNacimiento: Date){
        this.fechaDeNacimiento = fechaDeNacimiento;
    }


}