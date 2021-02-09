package com.erflagg.myproject

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(
        val title: String,
        val poster: Int,
        val description: String
) : Parcelable
