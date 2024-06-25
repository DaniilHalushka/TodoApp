package com.daniil.halushka.todoapp.domain.usecase

import com.daniil.halushka.todoapp.domain.repository.TodoRepositoryInterface
import javax.inject.Inject

class CountFinishedTodo @Inject constructor(
    private val todoRepository: TodoRepositoryInterface
) {
    suspend fun countTasks(): Int {
        return todoRepository.countFinishedTodo()
    }
}