package com.daniil.halushka.todoapp.domain.usecases

import com.daniil.halushka.todoapp.data.models.TodoItem
import com.daniil.halushka.todoapp.data.repository.TodoRepository

class UpdateTodoUseCase(
    private val repository: TodoRepository
)  {
    fun invoke(item: TodoItem) {
        repository.updateTodo(item)
    }
}