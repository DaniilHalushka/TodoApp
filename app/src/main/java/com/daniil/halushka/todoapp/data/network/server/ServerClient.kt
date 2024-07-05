package com.daniil.halushka.todoapp.data.network.server

import com.daniil.halushka.todoapp.constants.NetworkConstants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun createOkHttpClient(interceptor: NetworkInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()
}

fun createRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(NetworkConstants.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}