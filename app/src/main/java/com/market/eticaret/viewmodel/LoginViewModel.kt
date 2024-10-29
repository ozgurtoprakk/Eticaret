package com.market.eticaret.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.market.eticaret.model.Person
import com.market.eticaret.model.PersonResponse
import com.market.eticaret.service.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel:ViewModel() {
    val apiService = ApiService()

    fun login(username: String, password: String, onResult: (PersonResponse?) -> Unit) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                try {
                    val response = apiService.loginRequest(Person(username, password))
                    // Eğer response null değilse ve token varsa sonucu döndür, yoksa null döndür
                    if (response.token.isNotBlank()) {
                        response
                    } else {
                        null
                    }
                } catch (e: Exception) {
                    null // Hata durumunda null döndürüyoruz
                }
            }
            onResult(result)
        }
    }}