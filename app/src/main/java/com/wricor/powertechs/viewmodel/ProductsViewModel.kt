package com.wricor.powertechs.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wricor.powertechs.model.Products
import com.wricor.powertechs.repository.repo

class ProductsViewModel: ViewModel() {
    val repo = repo()
    fun productData(): LiveData<MutableList<Products>> {
        val mutableData = MutableLiveData<MutableList<Products>>()
        repo.getProductsData().observeForever {
            mutableData.value = it
        }
        return mutableData
    }
}