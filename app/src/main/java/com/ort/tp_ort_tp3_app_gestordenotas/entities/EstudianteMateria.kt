package com.ort.tp_ort_tp3_app_gestordenotas.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class EstudianteMateria() : Parcelable {

    private lateinit var estudiante: Estudiante
    private lateinit var materia: Materia
    private lateinit var nombreMateria: String
    private lateinit var estado: EstadoMateria
    private var nota: Int = 0

    constructor(estudiante: Estudiante, materia: Materia): this() {
        this.setEstudiante(estudiante);
        this.setMateria(materia);
        this.setEstado(EstadoMateria.PENDIENTE);
        this.setNota(0);
    }

    constructor(nombreMateria: String, estado: EstadoMateria, nota: Int): this() {
        this.setNombreMateria(nombreMateria)
        this.setEstado(estado);
        this.setNota(nota);
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

    fun getNombreMateria(): String{
        return this.nombreMateria
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

    private fun setNombreMateria(nombre: String){
        this.nombreMateria = nombre
    }



}