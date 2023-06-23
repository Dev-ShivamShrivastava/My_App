package com.example.myapp.network

import android.util.Log
import com.example.myapp.model.UserListResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Repository {

     fun fetchData(returnData:(UserListResponse)->Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val response =  RetrofitHelper.getRetrofitClient().getUserList()
            Log.e("response-->", response.body().toString())
            returnData(response.body()?: UserListResponse())
        }
    }
}