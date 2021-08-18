package com.intercamtest.zapata.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
/**
 * Created by Alberto Zapata on ago, 2021
 */
@Parcelize
data class Payment (
    val quantity1: Int,
    val quantity2: Int,
    val description: String,
    val isAuthorized: Boolean

): Parcelable