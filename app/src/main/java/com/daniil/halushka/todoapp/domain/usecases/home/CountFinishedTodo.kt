package com.daniil.halushka.todoapp.domain.usecases.home

import com.daniil.halushka.todoapp.domain.repository.TodoRepository
import javax.inject.Inject

class CountFinishedTodo @Inject constructor(
    private val todoRepository: TodoRepository
) {
    suspend fun countTasks() = todoRepository.countFinishedTodo()
}