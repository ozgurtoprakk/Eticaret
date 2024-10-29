package com.market.eticaret.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.market.eticaret.model.Product
import com.market.eticaret.service.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListViewModel:ViewModel() {
    val productApiService:ApiService=ApiService()
    val products: MutableLiveData<List<Product>> = MutableLiveData()

    fun bringProduct() {
        viewModelScope.launch(Dispatchers.IO) {
            val productListesi = productApiService.getProducts()
            withContext(Dispatchers.Main) {
                products.value=productListesi

            }
        }
    }

}