package com.example.chatversiontfg.common.retrofit.metodos

import android.util.Log
import com.example.chatversiontfg.CongresoApplication
import com.example.chatversiontfg.common.entities.BonoEntity
import com.example.chatversiontfg.common.retrofit.RetrofitClass
import com.example.chatversiontfg.common.retrofit.service.BonoService

class BonoMethods {

    suspend fun getBonosAsistente() : MutableList<BonoEntity> {
        val service = RetrofitClass().getRetrofit().create(BonoService::class.java)
        val result = service.getBonosAsistente(CongresoApplication.asistente.id)
        val bonos = result.body()!!

        return bonos
    }

    suspend fun deleteBonoAsistente(idBono: Long) {

        val service = RetrofitClass().getRetrofit().create(BonoService::class.java)

        try{

            val result = service.deleteBonoAsistente(CongresoApplication.asistente.id, idBono)

            CongresoApplication.bonos.remove(result.body())

        } catch (e: Exception) {

            Log.i("ERROR_DELETE", e.message.toString())

        }

    }

}