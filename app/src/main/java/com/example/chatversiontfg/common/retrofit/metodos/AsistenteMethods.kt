package com.example.chatversiontfg.common.retrofit.metodos

import com.example.chatversiontfg.CongresoApplication
import com.example.chatversiontfg.common.retrofit.RetrofitClass
import com.example.chatversiontfg.common.retrofit.service.AsistenteService

class AsistenteMethods {

    suspend fun putAsistente(username: String? = null, password: String? = null, email: String? = null) {

        val service = RetrofitClass().getRetrofit().create(AsistenteService::class.java)

        service.putAsistente(CongresoApplication.asistente.id, username!!, password!!, email!!)

    }

}