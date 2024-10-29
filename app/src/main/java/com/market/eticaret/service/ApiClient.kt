package com.market.eticaret.service

import com.market.eticaret.model.Person
import com.market.eticaret.model.PersonResponse
import com.market.eticaret.model.ProductResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiClient {

    @POST("/auth/login")
    suspend fun loginRequest(@Body request: Person): PersonResponse
    //Body,bizim yollcayacağımız kısım.Ona göre bir response gelecektir.

    @GET("/products")
    suspend fun getProducts(): ProductResponse

}