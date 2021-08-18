package com.intercamtest.zapata.utils

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Alberto Zapata on ago, 2021
 */
class DateTimeUtil {

    companion object{

        private const val TIME_PATTERN = "yyyy-MM-dd"

        @SuppressLint("SimpleDateFormat")
        fun getCurrentDate(): String {
            val simpleDateFormat = SimpleDateFormat(TIME_PATTERN)
            return  dateFormat(simpleDateFormat.format(Date()).toString())
        }

        private fun dateFormat(dateToConvert: String): String{
            val outputFormat: DateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
            val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

            val date = inputFormat.parse(dateToConvert)
            return outputFormat.format(date!!)

        }
    }
}