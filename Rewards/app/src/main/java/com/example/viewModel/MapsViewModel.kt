package com.example.viewModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MapsViewModel:ViewModel() {

    var text=MutableLiveData<String>()


    fun save(data:String)
    {
        text.value=data
    }



}