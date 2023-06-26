package com.example.myapp.network

import com.grapesnberries.curllogger.CurlLoggerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {


    @Singleton
    @Provides
    fun baseUrl():String{
        return "https://reqres.in"
    }

//    @ProductionURL
//    @Singleton
//    @Provides
//    fun baseUrlProduction():String{
//        return "https://reqres.inn"
//    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient
            .Builder()
            .readTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(loggingInterceptor)
//            .addInterceptor(Interceptor { chain ->
//                val newRequest = chain.request().newBuilder().apply {
//                    addHeader("client", "predictor")
//                }.build()
//                chain.proceed(newRequest)
//            })
            .addInterceptor(CurlLoggerInterceptor())
            .build()
    }


    @Singleton
    @Provides
    fun gsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
        baseUrl:String
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .build()


    @Provides
    @Singleton
    fun provideUserApiService(
        retrofit: Retrofit,
    ): RetrofitApi = retrofit.create(RetrofitApi::class.java)


    @Provides
    @Singleton
    fun exceptionHandler() = CoroutineExceptionHandler { _, t ->
        t.printStackTrace()
        CoroutineScope(Dispatchers.Main).launch {
            t.printStackTrace()
        }
    }


}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ProductionURL

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TestingUrl


