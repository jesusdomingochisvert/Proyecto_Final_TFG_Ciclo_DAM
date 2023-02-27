package com.example.chatversiontfg.common.retrofit

import com.example.chatversiontfg.common.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClass {

     fun getRetrofit(): Retrofit {
        val retrofit = Retrofit.Builder().baseUrl(Constants.CONGRESO_URL).addConverterFactory(
            GsonConverterFactory.create()).build()
        return retrofit
    }
}