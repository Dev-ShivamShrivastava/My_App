package com.example.myapp.network

import com.google.gson.JsonElement
import retrofit2.Response
import retrofit2.http.GET




interface RetrofitApi {
    @GET("/api/unknown")
    suspend fun doGetListResources(): Response<JsonElement>
}