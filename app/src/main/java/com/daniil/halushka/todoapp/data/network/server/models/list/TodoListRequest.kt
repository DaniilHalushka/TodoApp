package com.daniil.halushka.todoapp.data.network.server.models.list

import com.daniil.halushka.todoapp.data.network.server.models.item.TodoItemNetworkModel

data class TodoListRequest(
    val todoList: List<TodoItemNetworkModel>
)