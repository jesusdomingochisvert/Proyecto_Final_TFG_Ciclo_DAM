package com.example.chatversiontfg.common.entities

data class PatrocinadorEntity(
    var id: Long = 0,
    var empresaCif: EmpresaEntity,
    var paquete : PaqueteEntity,
    ){


}
