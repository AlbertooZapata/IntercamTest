package com.intercamtest.zapata.domain.payment

import com.intercamtest.zapata.data.model.Payment
import com.intercamtest.zapata.vo.Resource
/**
 * Created by Alberto Zapata on ago, 2021
 */
interface PaymentRepo {

    suspend fun getAllPayments(): Resource<List<Payment>>

}