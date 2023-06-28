package com.example.services

import com.example.data.Item

interface ApiHelper {

    suspend fun getUserdata(): List<Item>
}