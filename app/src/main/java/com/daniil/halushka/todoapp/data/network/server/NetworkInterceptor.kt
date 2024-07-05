package com.daniil.halushka.todoapp.data.network.server

import com.daniil.halushka.todoapp.data.network.repository.SharedPreferencesRepository
import com.daniil.halushka.todoapp.data.network.server.data.Revision
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class NetworkInterceptor @Inject constructor(
    private val sharedPrefRepository: SharedPreferencesRepository
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val myRequest: Request = chain.request()
            .newBuilder()
            .addHeader("X-Last-Known-Revision", sharedPrefRepository.getRevision().toString())
            .addHeader("Authorization", sharedPrefRepository.getAuthToken() ?: "")
            .build()
        val serverResponse = chain.proceed(myRequest)
        if (serverResponse.isSuccessful){
            val responseResult = serverResponse.peekBody(Long.MAX_VALUE).string()
            val gson = Gson()
            sharedPrefRepository.setRevision(
                gson.fromJson(responseResult, Revision::class.java).revision
            )
        }
        return serverResponse
    }
}
