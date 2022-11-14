package com.wricor.powertechs.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.wricor.powertechs.model.Products

class repo {
    fun getProductsData(): LiveData<MutableList<Products>> {
        val mutableData = MutableLiveData<MutableList<Products>>()

        FirebaseFirestore.getInstance().collection("productos").get()
            .addOnSuccessListener { result ->
                val listData = mutableListOf<Products>()
                for(document in result) {
                    val product = document.getString("product")
                    val price = document.getString("price")
                    val image = document.getString("image")
                    val prod = Products(product!!, price!!, image!!)
                    listData.add(prod)
                }
                mutableData.value = listData
            }
        return mutableData
    }
}