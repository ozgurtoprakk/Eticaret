package com.market.eticaret.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Review(
    @SerializedName("comment")
    val comment: String?,

    @SerializedName("rating")
    val rating: Double?
) : Parcelable
