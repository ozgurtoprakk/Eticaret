package com.market.eticaret.model

import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelize
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Product(
    @SerializedName("id")
    val id:Int,

    @SerializedName("title")
    val title:String?,

    @SerializedName("description")
    val description:String?,

    @SerializedName("price")
    val price:Double?,

    @SerializedName("reviews")
    val reviews: @RawValue List<Review>,

    @SerializedName("category")
    val category:String?,

    @SerializedName("thumbnail")
    val thumbNail:String?,

    @SerializedName("images")
    val images:List<String?>,


    ):Parcelable
