package com.daniil.halushka.todoapp.data.network.api

import com.daniil.halushka.todoapp.constants.NetworkConstants
import com.daniil.halushka.todoapp.data.network.api.models.item.TodoItemResponse
import com.daniil.halushka.todoapp.data.network.api.models.list.TodoListResponse
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TodoItemService {
    @GET("list")
    suspend fun getItems(
        @Header("Authorization") auth: String = NetworkConstants.tokenAuth
    ): TodoListResponse

    @PATCH("list")
    suspend fun updateItems(
        @Body body: RequestBody,
        @Header("X-Last-Known-Revision") revision: Int = NetworkConstants.revisionVersion,
        @Header("Authorization") auth: String = NetworkConstants.tokenAuth
    ): TodoListResponse

    @GET("list/{id}")
    suspend fun getItemById(
        @Path("id") path: String,
        @Header("Authorization") auth: String = NetworkConstants.tokenAuth,
    ): TodoItemResponse

    @POST("list")
    suspend fun addItem(
        @Header("X-Last-Known-Revision") revision: Int = NetworkConstants.revisionVersion,
        @Header("Authorization") auth: String = NetworkConstants.tokenAuth,
        @Body body: RequestBody,
    ): TodoItemResponse

    @PUT("list/{id}")
    suspend fun updateItem(
        @Path("id") path: String,
        @Body body: RequestBody,
        @Header("X-Last-Known-Revision") revision: Int = NetworkConstants.revisionVersion,
        @Header("Authorization") auth: String = NetworkConstants.tokenAuth
    ): TodoItemResponse

    @DELETE("list/{id}")
    suspend fun deleteItem(
        @Path("id") path: String,
        @Header("X-Last-Known-Revision") revision: Int = NetworkConstants.revisionVersion,
        @Header("Authorization") auth: String = NetworkConstants.tokenAuth
    ): TodoItemResponse

    @GET("authorize?response_type=token")
    suspend fun get(): Call<ResponseBody>
}