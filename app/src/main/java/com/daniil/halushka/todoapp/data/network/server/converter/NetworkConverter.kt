package com.daniil.halushka.todoapp.data.network.server.converter

import com.daniil.halushka.todoapp.data.models.TodoItem
import com.daniil.halushka.todoapp.data.network.server.models.item.TodoItemNetworkModel

object NetworkConverter {
    fun convertModelToEntity(todoItem: TodoItem): TodoItemNetworkModel{
        return TodoItemNetworkModel(
            id = todoItem.id,
            text = todoItem.text,
            importance =
        )
    }
}