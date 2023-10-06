package com.ort.tp_ort_tp3_app_gestordenotas.entities

class EstudianteMateria {

    private lateinit var estudiante: Estudiante
    private lateinit var materia: Materia
    private lateinit var estado: EstadoMateria
    private var nota: Int = 0

    constructor(estudiante: Estudiante, materia: Materia){
        this.setEstudiante(estudiante);
        this.setMateria(materia);
        this.setEstado(EstadoMateria.PENDIENTE);
        this.setNota(0);
    }

    constructor(e: Estudiante,m: Materia, estado: EstadoMateria, nota: Int){
        this.setEstudiante(e);
        this.setMateria(m);
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



}