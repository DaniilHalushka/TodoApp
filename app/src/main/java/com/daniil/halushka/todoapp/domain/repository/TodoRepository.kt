package com.daniil.halushka.todoapp.domain.repository

import com.daniil.halushka.todoapp.data.models.TodoItem

interface TodoRepository {
    fun getTodoList(): List<TodoItem>

    fun addTodoInList(todoItem: TodoItem)
}