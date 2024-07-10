package com.daniil.halushka.todoapp.data.network.api.models.list

import com.daniil.halushka.todoapp.data.network.api.models.item.TodoItemNetworkModel

data class TodoListResponse(
    val status: String,
    val todoList: List<TodoItemNetworkModel>,
    val revision: Int
)