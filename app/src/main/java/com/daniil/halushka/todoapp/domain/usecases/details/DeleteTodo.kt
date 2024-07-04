package com.daniil.halushka.todoapp.domain.usecases.details

import com.daniil.halushka.todoapp.domain.repository.TodoRepositoryInterface
import javax.inject.Inject

class DeleteTodo @Inject constructor(
    private val todoRepository: TodoRepositoryInterface
) {
    suspend fun deleteTodoInList(id: String) {
        todoRepository.deleteTodo(id)
    }
}