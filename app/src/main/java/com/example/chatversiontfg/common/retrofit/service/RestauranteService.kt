package com.example.chatversiontfg.common.retrofit.service

import com.example.chatversiontfg.common.entities.RestauranteEntity
import com.example.chatversiontfg.common.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RestauranteService {

    @GET(value = Constants.GET_RESTAURANTES_PATH)
    suspend fun getRestaurantes(): Response<MutableList<RestauranteEntity>>

    @GET(value= Constants.GET_RESTAURANTE_PATH)
    suspend fun getRestaurante(@Path(value = "id_puesto_comida") id: Long?):Response<RestauranteEntity>

}