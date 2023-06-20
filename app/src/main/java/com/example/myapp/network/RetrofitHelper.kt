package com.example.myapp.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitHelper {
    companion object{
        fun getRetrofitClient():RetrofitApi{
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            return Retrofit.Builder().baseUrl("https://reqres.in").addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build().create(RetrofitApi::class.java)
        }
    }
}