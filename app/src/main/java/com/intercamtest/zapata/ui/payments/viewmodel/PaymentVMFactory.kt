package com.intercamtest.zapata.ui.payments.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.intercamtest.zapata.domain.payment.PaymentRepo
/**
 * Created by Alberto Zapata on ago, 2021
 */
class PaymentVMFactory (private val paymentRepo: PaymentRepo):ViewModelProvider.Factory  {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(PaymentRepo::class.java).newInstance(paymentRepo)
    }
}