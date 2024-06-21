package com.daniil.halushka.todoapp.domain.usecases

import com.daniil.halushka.todoapp.data.repository.TodoRepository

class DeleteTodoUseCase(
    private val repository: TodoRepository
) {
    fun invoke(id: String) {
        repository.deleteTodo(id)
    }
}