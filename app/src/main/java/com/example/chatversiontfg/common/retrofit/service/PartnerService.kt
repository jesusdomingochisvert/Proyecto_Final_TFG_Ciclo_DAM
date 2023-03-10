package com.example.chatversiontfg.common.retrofit.service

import com.example.chatversiontfg.common.entities.SocioEntity
import com.example.chatversiontfg.common.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface PartnerService {

    @GET(value= Constants.GET_ALLPARTNERS_PATH)
    suspend fun getAllPartners():Response<List<SocioEntity>>

}