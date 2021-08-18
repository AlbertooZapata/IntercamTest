package com.intercamtest.zapata.data

import com.intercamtest.zapata.data.model.Beer
import com.intercamtest.zapata.data.model.Payment
import com.intercamtest.zapata.vo.Resource
import com.intercamtest.zapata.vo.RetrofitClient

/**
 * Created by Alberto Zapata on ago, 2021
 */
class DataSource {


    fun getAllPayments(): Resource<List<Payment>> {

        val paymentList = ArrayList<Payment>()
        paymentList.add(Payment(500, 100, "Cambio de Estatus", true))
        paymentList.add(Payment(600, 200, "Remesas", false))
        paymentList.add(Payment(700, 10, "Remesas Mayores", false))
        paymentList.add(Payment(500, 70, "Canjes", false))
        paymentList.add(Payment(300, 20, "Pagos por anticipos", true))
        paymentList.add(Payment(700, 10, "Remesas Mayores", false))


        return Resource.Success(paymentList)
    }

    suspend fun getBeers(page: Int, perPage: Int): Resource<List<Beer>> =//page, perPage
        Resource.Success(RetrofitClient.webService.getBeers())
}
