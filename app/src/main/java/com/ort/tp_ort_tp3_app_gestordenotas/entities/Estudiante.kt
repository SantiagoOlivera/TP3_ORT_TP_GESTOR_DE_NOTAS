package com.ort.tp_ort_tp3_app_gestordenotas.entities

class Estudiante : Usuario {

    private lateinit var materias: MutableList<EstudianteMateria>
    private var promedioMaterias: Double = 0.0


    constructor(
        usuario: String,
        email: String,
        password: String,
        idPersona: String
    ) : super(usuario, email, password, idPersona) {
        this.initMaterias();
    }

    constructor(
        usuario: String,
        email: String,
        password: String,
        persona: Persona
    ) : super(usuario, email, password, persona) {
        this.initMaterias();
    }

    fun getListMateriaPorAnio(anioMateria: AnioMateria): MutableList<EstudianteMateria>{
        var ret: MutableList<EstudianteMateria> = this.materias.filter {
                m -> m.getMateria()?.getAnioMateria() == anioMateria;
        }.toMutableList();
        return ret;
    }

    fun getListEstudianteMateria(): MutableList<EstudianteMateria> {
        return this.materias;
    }

    fun getListEstudianteMateriasInscripto(): MutableList<EstudianteMateria> {
        var ret : MutableList<EstudianteMateria> = mutableListOf();
        if(this.materias.count() > 0){
            ret = this.materias.filter { em -> em.getIsInscripto() }.toMutableList();
        }
        return ret;
    }

    private fun calcPromedio(){
        var prom: Double = this.getPromedioMaterias();
        this.setPromedioMaterias(prom);
    }

    private fun initMaterias(){
        this.materias = mutableListOf();
    }

    fun agregarEstudianteMateria(em: EstudianteMateria){
        this.materias.add(em);
    }

    private fun setMaterias(materias: MutableList<EstudianteMateria>){
        if(materias != null){
            this.materias = materias;
        }else{
            this.initMaterias();
        }
    }

    private fun getPromedioMaterias(): Double{
        var ret: Double = 0.0;
        var sum: Double = 0.0
        var count: Int = 0;
        for(mat in this.materias){
            if(mat.getEstado() == EstadoMateria.APROBADA){
                sum += mat.getNota();
                count++;
            }
        }
        if(count > 0){
            ret = sum / count;
        }

        return ret;
    }

    private fun setPromedioMaterias(promedioMaterias: Double){
        this.promedioMaterias = promedioMaterias
    }

}