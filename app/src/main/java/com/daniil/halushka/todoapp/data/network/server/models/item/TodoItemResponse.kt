package com.daniil.halushka.todoapp.data.network.server.models.item

data class TodoItemResponse(
    val status: String,
    val item: TodoItemNetworkModel,
    val revision: Int
)