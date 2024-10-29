package com.market.eticaret.model

import com.google.gson.annotations.SerializedName

data class PersonResponse(
    @SerializedName("token")
    val token: String,
)