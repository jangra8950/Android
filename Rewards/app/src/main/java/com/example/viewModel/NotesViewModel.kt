package com.example.viewModel



import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Item
import com.example.services.MyRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val repo: MyRepo): ViewModel() {

    var response=MutableLiveData<List<Item>>()
    init {
        getdata()
    }

    fun getdata(): MutableLiveData<List<Item>>
    {
       viewModelScope.launch{
            response.value=repo.getdata()

        }
        return response
    }

}