package com.example.services

import com.example.data.Item
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiHelperImpl @Inject constructor(private val apiService: ApiRequest):ApiHelper {
    override suspend fun getUserdata(): List<Item> =apiService.getUserdata()
}