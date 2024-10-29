package com.market.eticaret.model

import com.google.gson.annotations.SerializedName


data class Person(
    @SerializedName("username")
    val userName:String?,

    @SerializedName("password")
    val password:String?
)