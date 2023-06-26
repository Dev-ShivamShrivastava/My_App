package com.example.myapp.network

import android.util.Log
import com.example.myapp.model.UserListResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class Repository {

     fun fetchData(returnData:(UserListResponse)->Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitHelper.getRetrofitClient().getUserList()
                if (response.isSuccessful) {
                    returnData(response.body() ?: UserListResponse())
                } else {
                    returnData(UserListResponse())
                }
                Log.e("response-->", response.body().toString())

            }catch(e:Exception){
                returnData(UserListResponse())
                e.printStackTrace()
            }
        }
    }
}