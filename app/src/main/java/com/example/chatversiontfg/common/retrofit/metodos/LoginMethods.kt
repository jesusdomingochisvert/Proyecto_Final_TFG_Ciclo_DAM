package com.example.chatversiontfg.common.retrofit.metodos

import com.example.chatversiontfg.common.entities.AsistenteEntity
import com.example.chatversiontfg.common.entities.SocioEntity
import com.example.chatversiontfg.common.retrofit.RetrofitClass
import com.example.chatversiontfg.common.retrofit.service.LoginService

class LoginMethods {

    suspend fun comprobarAsistente(username:String,password:String) : AsistenteEntity {
        val service = RetrofitClass().getRetrofit().create(LoginService::class.java)
        val result = service.loginAsistente(username,password)
        return result
    }

    suspend fun getAsistente(id:Long) : AsistenteEntity {
        val service = RetrofitClass().getRetrofit().create(LoginService::class.java)
        val result = service.getAsistente(id)
        return result.body()!!
    }

    suspend fun comprobarSocio(id: Long) : SocioEntity {
        val service = RetrofitClass().getRetrofit().create(LoginService::class.java)
        val result = service.comprobarSocio(id)
        val socioEntity = result.body()!!

        return socioEntity
    }
}