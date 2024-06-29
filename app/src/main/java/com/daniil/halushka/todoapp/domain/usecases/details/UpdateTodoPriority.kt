package com.daniil.halushka.todoapp.domain.usecases.details

import com.daniil.halushka.todoapp.domain.repository.TodoRepositoryInterface
import javax.inject.Inject

class UpdateTodoPriority @Inject constructor(
    private val todoRepository: TodoRepositoryInterface
) {
    suspend fun updateTodoPriority(todoId: String, newPriority: String) {
        todoRepository.updateTodoPriority(todoId, newPriority)
    }
}