package com.daniil.halushka.todoapp.domain.usecases.details

import com.daniil.halushka.todoapp.domain.repository.TodoRepository
import javax.inject.Inject

class DeleteTodo @Inject constructor(
    private val todoRepository: TodoRepository
) {
    suspend fun deleteTodoInList(id: String) {
        todoRepository.deleteTodo(id)
    }
}