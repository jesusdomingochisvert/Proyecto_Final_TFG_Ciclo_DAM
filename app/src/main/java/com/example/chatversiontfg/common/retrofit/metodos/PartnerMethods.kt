package com.example.chatversiontfg.common.retrofit.metodos

import com.example.chatversiontfg.common.entities.SocioEntity
import com.example.chatversiontfg.common.retrofit.RetrofitClass
import com.example.chatversiontfg.common.retrofit.service.PartnerService

class PartnerMethods {

    suspend fun getPartners() : List<SocioEntity>? {
        val service = RetrofitClass().getRetrofit().create(PartnerService::class.java)
        val result = service.getAllPartners()
        val socios = result.body()

        return socios
    }
}