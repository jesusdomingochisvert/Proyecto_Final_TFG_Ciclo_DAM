package com.example.chatversiontfg.common.retrofit.service

import com.example.chatversiontfg.common.entities.BonoEntity
import com.example.chatversiontfg.common.utils.Constants
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface BonoService {

    @GET(value = Constants.GET_BONOS_ASISTENTE_PATH)
    suspend fun getBonosAsistente(@Path(value ="id_asistente")id: Long?) : Response<MutableList<BonoEntity>>


    @DELETE(value = Constants.DELETE_BONO_ASISTENTE_PATH)
    suspend fun deleteBonoAsistente(

        @Path(value = "id_asistente") idAsist: Long,
        @Path(value = "id_bono") idBono: Long

    ): Response<BonoEntity>

}