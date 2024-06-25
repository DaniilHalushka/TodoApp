package com.daniil.halushka.todoapp.domain.usecase

import com.daniil.halushka.todoapp.domain.repository.TodoRepositoryInterface

class CountFinishedTodo(
    private val todoRepository: TodoRepositoryInterface
) {
    suspend fun countTasks(): Int {
        return todoRepository.countFinishedTodo()
    }
}