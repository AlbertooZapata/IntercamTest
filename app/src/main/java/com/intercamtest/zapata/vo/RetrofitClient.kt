package com.intercamtest.zapata.vo

import com.google.gson.GsonBuilder
import com.intercamtest.zapata.domain.WebService
import com.intercamtest.zapata.utils.StringUtil
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val webService: WebService by lazy {
        Retrofit.Builder()
            .baseUrl(StringUtil.HOST)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}