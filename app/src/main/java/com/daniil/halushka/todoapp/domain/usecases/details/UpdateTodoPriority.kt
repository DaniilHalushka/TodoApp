package com.daniil.halushka.todoapp.domain.usecases.details

import com.daniil.halushka.todoapp.domain.repository.TodoRepository
import javax.inject.Inject

class UpdateTodoPriority @Inject constructor(
    private val todoRepository: TodoRepository
) {
    suspend fun updatePriorityInTodo(todoId: String, newPriority: String) {
        todoRepository.updateTodoPriority(todoId, newPriority)
    }
}