package com.daniil.halushka.todoapp.data.network.server

import com.daniil.halushka.todoapp.data.network.server.models.item.TodoItemRequest
import com.daniil.halushka.todoapp.data.network.server.models.item.TodoItemResponse
import com.daniil.halushka.todoapp.data.network.server.models.list.TodoListRequest
import com.daniil.halushka.todoapp.data.network.server.models.list.TodoListResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TodoItemService {
    @GET("list")
    suspend fun getTodoList(): TodoItemResponse

    @POST("list")
    suspend fun addTodoItem(
        @Body todoItem: TodoItemRequest
    ): TodoItemResponse

    @PUT("list/{id}")
    suspend fun changeTodoItem(
        @Path("id") id: String,
        @Body todoItem: TodoItemRequest
    ): TodoItemResponse

    @PATCH("list")
    suspend fun updateTodoList(
        @Body todoList: TodoListRequest
    ): TodoListResponse

    @DELETE("list/{id}")
    suspend fun deleteTodoItem(
        @Path("id") id: String
    ): TodoItemResponse
}