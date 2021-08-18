package com.intercamtest.zapata.ui.beers.viewmodel

import androidx.lifecycle.*
import com.intercamtest.zapata.domain.beer.BeerRepo
import com.intercamtest.zapata.vo.Resource
import kotlinx.coroutines.Dispatchers

/**
 * Created by Alberto Zapata on ago, 2021
 */
class BeerViewModel(private val beerRepo: BeerRepo) : ViewModel() {

    private val loadTriggerGetBeers = MutableLiveData(Unit)


    fun refreshGetBeers() {
        loadTriggerGetBeers.value = Unit
    }

    fun getBeers(page: Int, perPage: Int) = loadTriggerGetBeers.switchMap {
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                emit(beerRepo.getBeers(page, perPage))
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }
    }
}

