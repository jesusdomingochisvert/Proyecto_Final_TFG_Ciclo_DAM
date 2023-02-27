package com.example.chatversiontfg.common.retrofit.metodos

import com.example.chatversiontfg.common.entities.ActividadEntity
import com.example.chatversiontfg.common.retrofit.RetrofitClass
import com.example.chatversiontfg.homeModule.model.ActividadService

class ActividadMethods {

    suspend fun getActividades() : List<ActividadEntity>? {
        val service = RetrofitClass().getRetrofit().create(ActividadService::class.java)
        val result = service.getActividades()
        val actividades = result.body()

        return actividades
    }
}