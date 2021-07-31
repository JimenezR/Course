package com.jimenez.course.data.network

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkModule {

    private val TAG_NETWORK = "WS"

    fun provideRetrofit(baseURL: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.addInterceptor(
            HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    Log.i(TAG_NETWORK, message)
                }
            }).setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        okHttpClient.connectTimeout(100, TimeUnit.SECONDS)
        okHttpClient.readTimeout(100, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(100, TimeUnit.SECONDS)
        return okHttpClient.build()
    }

    fun <T> provideApi(retrofit: Retrofit, service: Class<T>) : T {
        return retrofit.create(service)
    }

}