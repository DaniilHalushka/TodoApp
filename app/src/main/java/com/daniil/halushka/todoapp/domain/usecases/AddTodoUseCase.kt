package com.daniil.halushka.todoapp.domain.usecases

import com.daniil.halushka.todoapp.data.models.TodoItem
import com.daniil.halushka.todoapp.data.repository.TodoRepository

class AddTodoUseCase(
    private val repository: TodoRepository
) {
    fun invoke(todoItem: TodoItem) {
        repository.addTodoInList(todoItem)
    }
}