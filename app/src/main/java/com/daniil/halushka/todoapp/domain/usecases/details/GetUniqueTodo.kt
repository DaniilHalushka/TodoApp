package com.daniil.halushka.todoapp.domain.usecases.details

import com.daniil.halushka.todoapp.domain.repository.TodoRepository
import javax.inject.Inject

class GetUniqueTodo @Inject constructor(
    private val todoRepository: TodoRepository
) {
    suspend fun getUniqueTodoFromList(id: String) = todoRepository.getUniqueTodo(id)

}