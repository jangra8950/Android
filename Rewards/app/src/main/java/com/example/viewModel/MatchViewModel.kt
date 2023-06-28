package com.example.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MatchViewModel: ViewModel() {

    val text1=MutableLiveData<String>()
    val text2=MutableLiveData<String>()
    val text3=MutableLiveData<String>().apply {
        if(text1==text2)
            "String Match"
        else
            "Do Not Match"
    }

    fun check()
    {
        if(text1==text2)
            text3.value="String Matched"
        else
            text3.value="Not matched"
    }

}