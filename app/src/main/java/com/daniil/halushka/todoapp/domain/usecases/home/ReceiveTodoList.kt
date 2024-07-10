package com.daniil.halushka.todoapp.domain.usecases.home

import com.daniil.halushka.todoapp.data.models.TodoItem
import com.daniil.halushka.todoapp.domain.repository.TodoRepository
import javax.inject.Inject

class ReceiveTodoList @Inject constructor(
    private val todoRepository: TodoRepository
) {
    suspend fun receiveTodoListFromList(): List<TodoItem> {
        return todoRepository.getTodoList()
    }
}