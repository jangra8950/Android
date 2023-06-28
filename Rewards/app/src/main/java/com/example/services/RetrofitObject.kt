package com.example.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    const val BASE_URL="https://jsonplaceholder.typicode.com"
    const val FAKE_URL="https://jsonplaceholder.typicod.co"
    val api : ApiRequest by lazy {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequest::class.java)
    }
}