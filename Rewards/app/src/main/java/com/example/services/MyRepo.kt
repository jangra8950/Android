package com.example.services

import com.example.data.Item
import com.example.services.RetrofitInstance
import javax.inject.Inject

class MyRepo @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getdata():List<Item>{
        return apiHelper.getUserdata()
        //return RetrofitInstance.api.getUserdata()
    }

}