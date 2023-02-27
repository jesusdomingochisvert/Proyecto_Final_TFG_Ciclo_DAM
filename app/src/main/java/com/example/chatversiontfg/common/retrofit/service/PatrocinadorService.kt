package com.example.chatversiontfg.common.retrofit.service

import com.example.chatversiontfg.common.entities.PatrocinadorEntity
import com.example.chatversiontfg.common.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface PatrocinadorService {

    @GET(value = Constants.GET_PATROCINADORES_PATH)
    suspend fun getPatrocinadores(): Response<List<PatrocinadorEntity>>
}