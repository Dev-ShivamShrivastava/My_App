package com.example.myapp.network

import com.example.myapp.model.UserListResponse
import com.google.gson.JsonElement
import retrofit2.Response
import retrofit2.http.GET




interface RetrofitApi {
    @GET("/api/users")
    suspend fun getUserList(): Response<UserListResponse>
}