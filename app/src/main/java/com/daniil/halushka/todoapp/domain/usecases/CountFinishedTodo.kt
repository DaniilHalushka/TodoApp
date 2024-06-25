package com.daniil.halushka.todoapp.domain.usecases

import com.daniil.halushka.todoapp.domain.repository.TodoRepositoryInterface
import javax.inject.Inject

class CountFinishedTodo @Inject constructor(
    private val todoRepository: TodoRepositoryInterface
) {
    suspend fun countTasks() = todoRepository.countFinishedTodo()
}