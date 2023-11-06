package com.ort.tp_ort_tp3_app_gestordenotas.entities

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize

@Parcelize
open class Usuario() : Parcelable {

    private lateinit var id: String
    private lateinit var usuario: String
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var idPersona: String
    private lateinit var persona: Persona

    constructor(
        usuario: String,
        email: String,
        password: String,
        idPersona: String
    ) : this() {
        this.setUsuario(usuario);
        this.setEmail(email);
        this.setPassword(password);
        this.setIdPersona(idPersona);
    }

    constructor(
        usuario: String,
        email: String,
        password: String,
        persona: Persona
    ) : this() {
        this.setUsuario(usuario);
        this.setEmail(email);
        this.setPassword(password);
        this.setPersona(persona);
    }

    fun getId():String{
        return this.id;
    }

    fun getUsuario(): String{
        return this.usuario;
    }

    fun getEmail(): String {
        return this.email;
    }

    fun getPassword(): String {
        return this.password;
    }

    fun getPersona(): Persona{
        return this.persona;
    }

    fun getIdPersona(): String {
        return this.idPersona;
    }

    fun setId(id: String){
        this.id = id;
    }
    private fun setUsuario(usuario: String){
        this.usuario =usuario;
    }
    private  fun setEmail(email: String){
        this.email = email;
    }
    private fun setPassword(password: String){
        this.password = password;
    }
    public fun setPersona(persona: Persona){
        this.persona = persona;
    }

    private fun setIdPersona(idPersona: String){
        this.idPersona = idPersona;
    }

}