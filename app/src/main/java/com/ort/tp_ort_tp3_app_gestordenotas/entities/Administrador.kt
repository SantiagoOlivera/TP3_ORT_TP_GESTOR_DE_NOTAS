package com.ort.tp_ort_tp3_app_gestordenotas.entities

import java.util.Date

class Administrador : Usuario {
    constructor(
        usuario: String,
        email: String,
        password: String,
        idPersona: String
    ) : super(usuario, email, password, idPersona) {
    }
    constructor(
        usuario: String,
        email: String,
        password: String,
        persona: Persona
    ) : super(usuario, email, password, persona) {
    }
}