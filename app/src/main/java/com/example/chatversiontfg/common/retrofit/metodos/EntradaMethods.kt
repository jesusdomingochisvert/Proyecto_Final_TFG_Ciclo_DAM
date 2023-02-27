package com.example.chatversiontfg.common.retrofit.metodos

import com.example.chatversiontfg.common.entities.EntradaEntity
import com.example.chatversiontfg.common.retrofit.RetrofitClass
import com.example.chatversiontfg.common.retrofit.service.EntradaService

class EntradaMethods {

    suspend fun getEntrada(id:Long) : EntradaEntity {
        val service = RetrofitClass().getRetrofit().create(EntradaService::class.java)
        val result = service.getEntrada(id)
        val entradaEntity = result.body()!!

        return entradaEntity
    }
}