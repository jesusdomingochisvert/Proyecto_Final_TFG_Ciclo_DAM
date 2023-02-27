package com.example.chatversiontfg.common.retrofit.service

import com.example.chatversiontfg.common.entities.EntradaEntity
import com.example.chatversiontfg.common.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EntradaService {

    @GET(value = Constants.GET_ENTRADA_PATH)
    suspend fun getEntrada(

        @Path(value = "id_asistente") id_asistente: Long

    ): Response<EntradaEntity>

}