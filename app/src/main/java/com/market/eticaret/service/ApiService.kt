package com.market.eticaret.service

import com.market.eticaret.model.Person
import com.market.eticaret.model.PersonResponse
import com.market.eticaret.model.Product
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {

    //Bir retrofit nesnesi oluşturduk,bu nesne bizim http isteklerimizi apiye iletecek.
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dummyjson.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiClient::class.java)

    suspend fun loginRequest(person:Person): PersonResponse {
        return retrofit.loginRequest(person)
    }

    suspend fun getProducts(): List<Product> {
        return retrofit.getProducts().products
    }
}