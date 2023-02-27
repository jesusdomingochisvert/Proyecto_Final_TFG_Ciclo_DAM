package com.example.chatversiontfg.common.retrofit.service

import com.example.chatversiontfg.common.entities.AsistenteEntity
import com.example.chatversiontfg.common.utils.Constants
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.PUT
import retrofit2.http.Path

interface AsistenteService {

    @FormUrlEncoded
    @PUT(value = Constants.PUT_ASISTENTE_PATH)
    suspend fun putAsistente(

        @Path("id") id: Long,

        @Field("nombreUsuario") username: String,
        @Field("contrasenya") password: String,
        @Field("correo") email: String,

    ): Response<AsistenteEntity>

}