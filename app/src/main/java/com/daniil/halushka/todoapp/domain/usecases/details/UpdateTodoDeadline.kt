package com.daniil.halushka.todoapp.domain.usecases.details

import com.daniil.halushka.todoapp.domain.repository.TodoRepositoryInterface
import javax.inject.Inject

class UpdateTodoDeadline @Inject constructor(
    private val todoRepository: TodoRepositoryInterface
) {
    suspend fun updateTodoDeadline(todoId: String, newDeadline: String) {
        todoRepository.updateTodoPriority(todoId, newDeadline)
    }
}