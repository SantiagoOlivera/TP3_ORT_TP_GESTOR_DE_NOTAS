package com.ort.tp_ort_tp3_app_gestordenotas.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class EstudianteMateria() : Parcelable {

    private lateinit var idPersona: String
    private lateinit var idMateria: String
    private lateinit var estudiante: Estudiante
    private lateinit var materia: Materia
    private lateinit var estado: EstadoMateria
    private var isInscripto: Boolean = false;
    private var nota: Int = 0;

    constructor(idPersona: String, idMateria: String, estado: EstadoMateria, nota: Int): this() {
        this.setIdPersona(idPersona);
        this.setIdMateria(idMateria);
        this.setEstado(estado);
        this.setNota(nota);
    }

    constructor(estudiante: Estudiante, materia: Materia): this() {
        this.setEstudiante(estudiante);
        this.setMateria(materia);
        this.setEstado(EstadoMateria.PENDIENTE);
        this.setNota(0);
    }

    constructor(e: Estudiante,m: Materia, estado: EstadoMateria, nota: Int): this() {
        this.setEstudiante(e);
        this.setMateria(m);
        this.setEstado(estado);
        this.setNota(nota);
    }

    fun getIsInscripto(): Boolean{
        return this.isInscripto;
    }

    fun getMateria(): Materia{
        return this.materia;
    }

    fun getEstudiante(): Estudiante{
        return this.estudiante;
    }

    fun getNota(): Int{
        return this.nota;
    }

    fun getEstado(): EstadoMateria{
        return this.estado;
    }

    private fun setIdMateria(idMateria: String){
        this.idMateria = idMateria;
    }

    private fun setIdPersona(idPersona: String){
        this.idPersona = idPersona;
    }

    private fun setNota(nota: Int){
        this.nota = nota;
    }

    private fun setEstado(estado: EstadoMateria){
        this.estado = estado;
    }

    private fun setMateria(materia: Materia){
        this.materia = materia;
    }
    private fun setEstudiante(estudiante: Estudiante){
        this.estudiante = estudiante;
    }

    fun setIsInscripto( isInscripto: Boolean ){
        this.isInscripto = isInscripto;
    }




}