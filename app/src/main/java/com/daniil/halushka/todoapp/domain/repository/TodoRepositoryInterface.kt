package com.daniil.halushka.todoapp.domain.repository

import com.daniil.halushka.todoapp.data.models.TodoItem

interface TodoRepositoryInterface {
    fun getTodoList(): List<TodoItem>

    fun addTodoInList(todoItem: TodoItem)

    fun updateTodo(updatedTodo: TodoItem)

    fun deleteTodo(id: String)
}