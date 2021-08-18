package com.intercamtest.zapata.ui.beers.BeerVMFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.intercamtest.zapata.domain.beer.BeerRepo
import com.intercamtest.zapata.domain.payment.PaymentRepo
/**
 * Created by Alberto Zapata on ago, 2021
 */
class BeerVMFactory (private val beerRepo: BeerRepo):ViewModelProvider.Factory  {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(BeerRepo::class.java).newInstance(beerRepo)
    }
}