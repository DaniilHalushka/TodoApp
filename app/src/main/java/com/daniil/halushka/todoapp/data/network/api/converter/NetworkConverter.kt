package com.daniil.halushka.todoapp.data.network.api.converter

import com.daniil.halushka.todoapp.constants.Priority
import com.daniil.halushka.todoapp.data.models.TodoItem
import com.daniil.halushka.todoapp.data.network.api.models.item.TodoItemNetworkModel

object NetworkConverter {
    fun convertModelToEntity(todoItem: TodoItem): TodoItemNetworkModel {
        return TodoItemNetworkModel(
            id = todoItem.id,
            text = todoItem.text,
            importance = Priority.convertPriorityToImportance((todoItem.priority)),
            isDone = todoItem.isDone,
            createdAt = todoItem.startDate,
            deadline = todoItem.deadline,
            changedAt = todoItem.changeDate ?: todoItem.startDate
        )
    }

    fun convertEntityToModel(todoItemNetworkModel: TodoItemNetworkModel) =
        TodoItem(
            id = todoItemNetworkModel.id,
            text = todoItemNetworkModel.text,
            priority = Priority.convertImportanceToPriority(todoItemNetworkModel.importance),
            isDone = todoItemNetworkModel.isDone,
            startDate = todoItemNetworkModel.createdAt,
            deadline = todoItemNetworkModel.deadline,
            changeDate = todoItemNetworkModel.changedAt
        )
}