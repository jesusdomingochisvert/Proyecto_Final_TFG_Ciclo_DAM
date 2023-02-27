package com.example.chatversiontfg.common.retrofit.metodos

import com.example.chatversiontfg.common.entities.RestauranteEntity
import com.example.chatversiontfg.common.retrofit.RetrofitClass
import com.example.chatversiontfg.common.retrofit.service.RestauranteService

class RestauranteMethods {

    suspend fun getRestaurantes() : MutableList<RestauranteEntity>? {
        val service = RetrofitClass().getRetrofit().create(RestauranteService::class.java)
        val result = service.getRestaurantes()
        val restaurantes = result.body()

        return restaurantes
    }

    //RESTAURANTEINFOACTIVITY
    suspend fun getRestaurante(id:Long?) : RestauranteEntity{
        val service = RetrofitClass().getRetrofit().create(RestauranteService::class.java)
        val result = service.getRestaurante(id)
        val restauranteEntity = result.body()!!

        return restauranteEntity
    }
}