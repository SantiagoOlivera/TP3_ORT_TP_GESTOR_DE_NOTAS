package com.ort.tp_ort_tp3_app_gestordenotas.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
class EstudianteMateria(
    var estudianteAux: Estudiante? = null,
    var materiaAux: @RawValue Materia? = null,
    var nombreMateriaAux: String? = "",
    var estadoAux: EstadoMateria = EstadoMateria.PENDIENTE
) : Parcelable {

    private var listaParciales: MutableList<Parcial> = mutableListOf()
    private var estudiante: Estudiante? = estudianteAux
    private var materia: Materia? = materiaAux
    private var nombreMateria: String? = nombreMateriaAux
    private var estado: EstadoMateria = estadoAux
    private var nota: Int = 0
    private lateinit var idPersona: String
    private lateinit var idMateria: String
    private var isInscripto: Boolean = false;


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

    fun getListaParciales(): MutableList<Parcial> {
        return this.listaParciales
    }

    fun getIsInscripto(): Boolean{
        return this.isInscripto;
    }

    fun getMateria(): Materia?{
        return this.materia;
    }

    fun getEstudiante(): Estudiante?{
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

    fun setNota(nota: Int){
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