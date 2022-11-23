package com.wricor.powertechs.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.wricor.powertechs.model.Shop

class shopsRepo {
    fun getShopsData(): LiveData<MutableList<Shop>> {
        val mutableData = MutableLiveData<MutableList<Shop>>()

        FirebaseFirestore.getInstance().collection("compras").get()
            .addOnSuccessListener { result ->
                val listData = mutableListOf<Shop>()
                for(document in result) {
                    val product = document.getString("product")
                    val price = document.getString("price")
                    val image = document.getString("image")
                    val prod = Shop(product!!, price!!, image!!)
                    listData.add(prod)
                }
                mutableData.value = listData
            }
        return mutableData
    }
}