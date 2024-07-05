package com.daniil.halushka.todoapp.domain.usecases.home

import com.daniil.halushka.todoapp.domain.repository.TodoRepository
import javax.inject.Inject

class FinishTodo @Inject constructor(
    private val todoRepository: TodoRepository
) {
    suspend fun finishTodoFromList(todoId: String, isTodoDone: Boolean) {
        todoRepository.finishTodo(todoId, isTodoDone)
    }
}