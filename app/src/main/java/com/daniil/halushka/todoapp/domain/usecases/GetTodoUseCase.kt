package com.daniil.halushka.todoapp.domain.usecases

import com.daniil.halushka.todoapp.data.models.TodoItem
import com.daniil.halushka.todoapp.data.repository.TodoRepository

class GetTodoUseCase(
    private val repository: TodoRepository
) {
    fun invoke(): List<TodoItem> {
        return repository.getTodoList()
    }
}