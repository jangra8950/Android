package com.example.services

import com.example.data.Item
import retrofit2.http.GET

interface ApiRequest {

    @GET("/users")
    suspend fun getUserdata(): List<Item>
}