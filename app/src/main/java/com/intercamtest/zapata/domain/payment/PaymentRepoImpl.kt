package com.intercamtest.zapata.domain.payment

import com.intercamtest.zapata.data.DataSource
import com.intercamtest.zapata.data.model.Payment
import com.intercamtest.zapata.vo.Resource

class PaymentRepoImpl(private val dataSource: DataSource) : PaymentRepo {

    override suspend fun getAllPayments(): Resource<List<Payment>> {

        return dataSource.getAllPayments()
    }
}