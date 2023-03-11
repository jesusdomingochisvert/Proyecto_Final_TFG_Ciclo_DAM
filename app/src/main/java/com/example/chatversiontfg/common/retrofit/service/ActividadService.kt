package com.example.chatversiontfg.homeModule.model

import com.example.chatversiontfg.common.entities.ActividadEntity
import com.example.chatversiontfg.common.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ActividadService {

    @GET(value = Constants.GET_ACTIVIDADES_PATH)
    suspend fun getActividades(): Response<List<ActividadEntity>>

    @GET(value= Constants.GET_ACTIVIDAD_PATH)
    suspend fun getActividad(@Path(value = "id") id: Long?): Response<ActividadEntity>

}