package com.example.myapp.network

import android.util.Log
import com.example.myapp.model.UserListResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class Repository @Inject constructor(private val retrofitApi: RetrofitApi) {

    fun fetchData(returnData: (UserListResponse) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitApi.getUserList()
                returnData(response.body() ?: UserListResponse())
                Log.e("response-->", response.body().toString())
            } catch (e: Exception) {
                returnData(UserListResponse())
                e.printStackTrace()
            }
        }
    }
}