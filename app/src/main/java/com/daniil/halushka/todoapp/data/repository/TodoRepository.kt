package com.daniil.halushka.todoapp.data.repository

import androidx.compose.runtime.mutableStateListOf
import com.daniil.halushka.todoapp.constants.Constants
import com.daniil.halushka.todoapp.data.models.TodoItem
import com.daniil.halushka.todoapp.domain.repository.TodoRepository

class TodoRepository : TodoRepository {
    private val todoList = mutableStateListOf(
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
        ),
        TodoItem(
            id = "11",
            text = "Chill",
            priority = Constants.URGENT_PRIORITY,
            startDate = System.currentTimeMillis(),
            isDone = false,
            deadline = System.currentTimeMillis() + 10800000
        ),
        TodoItem(
            id = "12",
            text = "Work",
            priority = Constants.LOW_PRIORITY,
            startDate = System.currentTimeMillis(),
            changeDate = System.currentTimeMillis(),
            isDone = true,
            deadline = System.currentTimeMillis() + 10800000
        ),
        TodoItem(
            id = "13",
            text = "Repeat all todo",
            priority = Constants.USUAL_PRIORITY,
            startDate = System.currentTimeMillis(),
            isDone = true
        ),
        TodoItem(
            id = "14",
            text = "Plant trees",
            priority = Constants.URGENT_PRIORITY,
            startDate = System.currentTimeMillis(),
            isDone = false,
            deadline = System.currentTimeMillis() + 7200000
        ),
        TodoItem(
            id = "15",
            text = "Go to the fest",
            priority = Constants.USUAL_PRIORITY,
            startDate = System.currentTimeMillis(),
            isDone = false
        ),
    )


    override fun getTodoList(): List<TodoItem> = todoList

    override fun addTodoInList(todoItem: TodoItem) {
        todoList.add(todoItem)
    }

    override fun updateTodo(updatedTodo: TodoItem) {
        todoList.indexOfFirst { todo -> todo.id == updatedTodo.id }
            .takeIf { result -> result != -1 }?.let { index ->
                todoList[index] = updatedTodo
            }
    }

    override fun deleteTodo(id: String) {
        todoList.removeAll { todo -> todo.id == id }
    }
}