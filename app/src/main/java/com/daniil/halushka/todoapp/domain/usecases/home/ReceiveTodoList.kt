package com.daniil.halushka.todoapp.domain.usecases.home

import com.daniil.halushka.todoapp.data.models.TodoItem
import com.daniil.halushka.todoapp.domain.repository.TodoRepositoryInterface
import javax.inject.Inject

class ReceiveTodoList @Inject constructor(
    private val todoRepository: TodoRepositoryInterface
) {
    suspend fun receiveTodoList(): List<TodoItem> {
        return todoRepository.getTodoList()
    }
}