package com.daniil.halushka.todoapp.domain.usecase

import com.daniil.halushka.todoapp.domain.repository.TodoRepositoryInterface

class FinishTodo(
    private val todoRepository: TodoRepositoryInterface
) {
    suspend fun finishTodo(todoId: String, isTodoDone: Boolean) {
        todoRepository.finishTodo(todoId, isTodoDone)
    }
}