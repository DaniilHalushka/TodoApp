package com.daniil.halushka.todoapp.domain.usecases.home

import com.daniil.halushka.todoapp.domain.repository.TodoRepositoryInterface
import javax.inject.Inject

class CountFinishedTodo @Inject constructor(
    private val todoRepository: TodoRepositoryInterface
) {
    suspend fun countTasks() = todoRepository.countFinishedTodo()
}