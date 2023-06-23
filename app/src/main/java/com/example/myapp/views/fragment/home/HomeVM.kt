package com.example.myapp.views.fragment.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapp.model.UserListResponse
import com.example.myapp.network.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeVM:ViewModel(){

    private var repository = Repository()

    val responseLive = MutableLiveData(UserListResponse())

    init {
        callApi()
    }

    fun callApi(){
            repository.fetchData(){
                CoroutineScope(Dispatchers.Main).launch {
                responseLive.value = it
            }
        }
    }

}