package com.daniil.halushka.todoapp.domain.repository

import com.daniil.halushka.todoapp.data.models.TodoItem

interface TodoRepositoryInterface {
    suspend fun getTodoList(): List<TodoItem>
    suspend fun getUniqueTodo(id: String): TodoItem
    suspend fun addTodoInList(todoItem: TodoItem)
    suspend fun updateTodo(updatedTodo: TodoItem)
    suspend fun deleteTodo(id: String)
    suspend fun countFinishedTodo(): Int
    suspend fun finishTodo(todoId: String, isTodoDone: Boolean)

}