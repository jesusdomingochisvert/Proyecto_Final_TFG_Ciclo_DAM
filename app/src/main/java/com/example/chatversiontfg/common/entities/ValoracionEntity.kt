package com.example.chatversiontfg.common.entities

data class ValoracionEntity(
    var asistente : AsistenteEntity,
    var evento : EventoEntity,
    var valoracion : String = ""
)
