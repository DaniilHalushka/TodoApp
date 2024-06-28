package com.daniil.halushka.todoapp.domain.usecases.home

import com.daniil.halushka.todoapp.domain.repository.TodoRepositoryInterface
import javax.inject.Inject

class FinishTodo @Inject constructor(
    private val todoRepository: TodoRepositoryInterface
) {
    suspend fun finishTodo(todoId: String, isTodoDone: Boolean) {
        todoRepository.finishTodo(todoId, isTodoDone)
    }
}