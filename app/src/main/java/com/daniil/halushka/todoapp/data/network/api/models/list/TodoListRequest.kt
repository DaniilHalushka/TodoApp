package com.daniil.halushka.todoapp.data.network.api.models.list

import com.daniil.halushka.todoapp.data.network.api.models.item.TodoItemNetworkModel

data class TodoListRequest(
    val todoList: List<TodoItemNetworkModel>
)