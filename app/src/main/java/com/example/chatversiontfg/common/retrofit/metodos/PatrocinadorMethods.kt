package com.example.chatversiontfg.common.retrofit.metodos

import com.example.chatversiontfg.common.entities.PatrocinadorEntity
import com.example.chatversiontfg.common.retrofit.RetrofitClass
import com.example.chatversiontfg.common.retrofit.service.PatrocinadorService

class PatrocinadorMethods {
    suspend fun getPatrocinadores() : List<PatrocinadorEntity>? {
        val service = RetrofitClass().getRetrofit().create(PatrocinadorService::class.java)
        val result = service.getPatrocinadores()
        val patrocinadores = result.body()

        return patrocinadores
    }
}