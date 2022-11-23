package com.wricor.powertechs.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wricor.powertechs.model.Shop
import com.wricor.powertechs.repository.shopsRepo

class ShopsViewModel: ViewModel() {
    val repo = shopsRepo()
    fun fetchShopsData(): LiveData<MutableList<Shop>> {
        val mutableData = MutableLiveData<MutableList<Shop>>()
        repo.getShopsData().observeForever {
            mutableData.value = it
        }
        return mutableData
    } }