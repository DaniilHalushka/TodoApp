package com.daniil.halushka.todoapp.data.network.server.models.list

import com.daniil.halushka.todoapp.data.network.server.models.item.TodoItemNetworkModel

data class TodoListResponse(
    val status: String,
    val todoList: List<TodoItemNetworkModel>,
    val revision: Int
)