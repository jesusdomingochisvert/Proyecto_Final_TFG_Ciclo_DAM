package com.example.chatversiontfg.common.entities

data class SocioEntity(

    var idSocio: Long,
    var cargo:String = "",
    var asistente : AsistenteEntity,
    var empresa : EmpresaEntity

)

