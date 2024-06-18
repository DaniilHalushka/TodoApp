package com.daniil.halushka.todoapp.data.repository

import com.daniil.halushka.todoapp.constants.Constants
import com.daniil.halushka.todoapp.data.model.TodoItem

class TodoRepository {
    private val todoList = mutableListOf<TodoItem>()

    init {
        todoList.addAll(createArtificialTodoList())
    }

    fun getTodoList(): List<TodoItem> {
        return todoList
    }

    fun addTodoInList(todoItem: TodoItem) {
        todoList.add(todoItem)
    }

    private fun createArtificialTodoList(): List<TodoItem> {
        return listOf(
            TodoItem(
                id = "1",
                text = "Create TodoItem",
                priority = Constants.LOW_PRIORITY,
                startDate = System.currentTimeMillis(),
                isDone = true
            ),
            TodoItem(
                id = "2",
                text = "Create TodoRepository",
                priority = Constants.USUAL_PRIORITY,
                startDate = System.currentTimeMillis(),
                isDone = true,
                deadline = System.currentTimeMillis() + 3600000,
            ),
            TodoItem(
                id = "3",
                text = "Create functions in repo",
                priority = Constants.URGENT_PRIORITY,
                startDate = System.currentTimeMillis(),
                isDone = false,
                deadline = System.currentTimeMillis() + 1234567,
                changeDate = System.currentTimeMillis()
            ),
            TodoItem(
                id = "4",
                text = "Create todo list",
                priority = Constants.USUAL_PRIORITY,
                startDate = System.currentTimeMillis(),
                isDone = true
            ),
            TodoItem(
                id = "5",
                text = "Write todo\'s",
                priority = Constants.LOW_PRIORITY,
                startDate = System.currentTimeMillis(),
                isDone = false,
                deadline = System.currentTimeMillis() + 86400000,
                changeDate = System.currentTimeMillis()
            ),
            TodoItem(
                id = "6",
                text = "Create XML files",
                priority = Constants.URGENT_PRIORITY,
                startDate = System.currentTimeMillis(),
                isDone = true,
                changeDate = System.currentTimeMillis()
            ),
            TodoItem(
                id = "7",
                text = "Create recycler",
                priority = Constants.LOW_PRIORITY,
                startDate = System.currentTimeMillis(),
                isDone = false
            ),
            TodoItem(
                id = "8",
                text = "Setup recycler parts",
                priority = Constants.USUAL_PRIORITY,
                isDone = false,
                startDate = System.currentTimeMillis(),
                changeDate = System.currentTimeMillis()
            ),
            TodoItem(
                id = "9",
                text = "Setup navigation",
                priority = Constants.URGENT_PRIORITY,
                startDate = System.currentTimeMillis(),
                isDone = false,
                deadline = System.currentTimeMillis() + 7200000
            ),
            TodoItem(
                id = "10",
                text = "Plan vacation",
                priority = Constants.LOW_PRIORITY,
                startDate = System.currentTimeMillis(),
                isDone = false
            )
        )
    }
}