package com.daniil.halushka.todoapp.domain.usecases.details

import com.daniil.halushka.todoapp.data.models.TodoItem
import com.daniil.halushka.todoapp.domain.repository.TodoRepositoryInterface
import javax.inject.Inject

class SaveTodo @Inject constructor(
    private val todoRepository: TodoRepositoryInterface
) {
    suspend fun saveTodoInList(todoItem: TodoItem) {
        todoRepository.saveTodo(todoItem)
    }
}