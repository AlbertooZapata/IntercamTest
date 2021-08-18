package com.intercamtest.zapata.ui.payments.viewmodel

import androidx.lifecycle.*
import com.intercamtest.zapata.domain.payment.PaymentRepo
import com.intercamtest.zapata.vo.Resource
import kotlinx.coroutines.Dispatchers

/**
 * Created by Alberto Zapata on ago, 2021
 */
class PaymentViewModel(private val paymentRepo: PaymentRepo) : ViewModel() {

    private val loadTriggerGetAllPayments = MutableLiveData(Unit)

    fun refreshGetAllServices() {
        loadTriggerGetAllPayments.value = Unit
    }

    fun getAllPayments() = loadTriggerGetAllPayments.switchMap {
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                emit(paymentRepo.getAllPayments())
            } catch (e: Exception) {
                emit(Resource.Failure(e))
            }
        }
    }
}

