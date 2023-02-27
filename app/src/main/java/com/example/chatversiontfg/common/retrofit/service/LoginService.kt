package com.example.chatversiontfg.common.retrofit.service

import com.example.chatversiontfg.common.entities.AsistenteEntity
import com.example.chatversiontfg.common.entities.SocioEntity
import com.example.chatversiontfg.common.utils.Constants
import retrofit2.Response
import retrofit2.http.*

interface LoginService {

    @FormUrlEncoded
    @POST(value=Constants.POST_LOGIN_PATH)
    suspend fun loginAsistente(@Field("username") username: String, @Field("password") password: String): AsistenteEntity

    @GET(value= Constants.GET_ASISTENTE_PATH)
    suspend fun getAsistente(@Path(value = "id") id: Long?): Response<AsistenteEntity>
    @GET(value= Constants.GET_SOCIO)
    suspend fun comprobarSocio(@Path(value = "id_asistente") id: Long): Response<SocioEntity>

}

