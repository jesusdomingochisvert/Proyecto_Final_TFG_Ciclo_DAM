package com.example.chatversiontfg.common.entities

data class AjenoEntity(

    var id: Long = 0,
    var asistente: AsistenteEntity,
    var actividades: List<ActividadEntity>

)