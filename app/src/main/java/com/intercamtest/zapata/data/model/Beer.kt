package com.intercamtest.zapata.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Alberto Zapata on ago, 2021
 */
@Parcelize
data class Beer(
    val description: String,
    val image_url: String,
    val name: String,
    val first_brewed: String,
    val tagline: String
) : Parcelable