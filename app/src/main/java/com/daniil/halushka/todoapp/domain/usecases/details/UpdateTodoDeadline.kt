package com.daniil.halushka.todoapp.domain.usecases.details

import com.daniil.halushka.todoapp.domain.repository.TodoRepository
import javax.inject.Inject

class UpdateTodoDeadline @Inject constructor(
    private val todoRepository: TodoRepository
) {
    suspend fun updateDeadlineInTodo(todoId: String, newDeadline: String) {
        todoRepository.updateTodoPriority(todoId, newDeadline)
    }
}