package com.intercamtest.zapata.domain

import com.intercamtest.zapata.data.model.Beer
import retrofit2.http.*

interface WebService {

    @GET("/v2/beers")
    suspend fun  getBeers(
//        @Query("page") page: Int,
//        @Query("per_page") perPage: Int
    ): List<Beer>
}