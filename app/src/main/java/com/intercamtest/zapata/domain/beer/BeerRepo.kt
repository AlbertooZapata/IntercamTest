package com.intercamtest.zapata.domain.beer

import com.intercamtest.zapata.data.model.Beer
import com.intercamtest.zapata.vo.Resource
/**
 * Created by Alberto Zapata on ago, 2021
 */
interface BeerRepo {

    suspend fun getBeers(page: Int, perPage: Int): Resource<List<Beer>>

}