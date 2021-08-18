package com.intercamtest.zapata.domain.beer

import com.intercamtest.zapata.data.DataSource
import com.intercamtest.zapata.data.model.Beer
import com.intercamtest.zapata.vo.Resource

class BeerRepoImpl(private val dataSource: DataSource) : BeerRepo {

    override suspend fun getBeers(page: Int, perPage: Int): Resource<List<Beer>> {
        return dataSource.getBeers(page, perPage)
    }
}