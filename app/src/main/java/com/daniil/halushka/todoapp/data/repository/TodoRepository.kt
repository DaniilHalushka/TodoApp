package com.daniil.halushka.todoapp.data.repository

import androidx.compose.runtime.mutableStateListOf
import com.daniil.halushka.todoapp.constants.Priority
import com.daniil.halushka.todoapp.data.models.TodoItem
import com.daniil.halushka.todoapp.domain.repository.TodoRepositoryInterface

class TodoRepository : TodoRepositoryInterface {
    private val todoList = mutableStateListOf(
        TodoItem(
            id = "1",
            text = "Create TodoItem",
            priority = Priority.LOW_PRIORITY,
            startDate = System.currentTimeMillis(),
            isDone = true
        ),
        TodoItem(
            id = "2",
            text = "Create TodoRepository",
            priority = Priority.USUAL_PRIORITY,
            startDate = System.currentTimeMillis(),
            isDone = true,
            deadline = System.currentTimeMillis() + 3600000,
        ),
        TodoItem(
            id = "3",
            text = "Create functions in repo",
            priority = Priority.URGENT_PRIORITY,
            startDate = System.currentTimeMillis(),
            isDone = false,
            deadline = System.currentTimeMillis() + 123456789,
            changeDate = System.currentTimeMillis()
        ),
        TodoItem(
            id = "4",
            text = "Create todo list",
            priority = Priority.USUAL_PRIORITY,
            startDate = System.currentTimeMillis(),
            isDone = true
        ),
        TodoItem(
            id = "5",
            text = "Write todo\'s",
            priority = Priority.LOW_PRIORITY,
            startDate = System.currentTimeMillis(),
            isDone = false,
            deadline = System.currentTimeMillis() + 86400000,
            changeDate = System.currentTimeMillis()
        ),
        TodoItem(
            id = "6",
            text = "Create XML files",
            priority = Priority.URGENT_PRIORITY,
            startDate = System.currentTimeMillis(),
            isDone = true,
            changeDate = System.currentTimeMillis()
        ),
        TodoItem(
            id = "7",
            text = "Create recycler",
            priority = Priority.LOW_PRIORITY,
            startDate = System.currentTimeMillis(),
            isDone = false
        ),
        TodoItem(
            id = "8",
            text = "Setup recycler parts",
            priority = Priority.USUAL_PRIORITY,
            isDone = false,
            startDate = System.currentTimeMillis(),
            changeDate = System.currentTimeMillis()
        ),
        TodoItem(
            id = "9",
            text = "Setup navigation",
            priority = Priority.URGENT_PRIORITY,
            startDate = System.currentTimeMillis(),
            isDone = false,
            deadline = System.currentTimeMillis() + 7200000
        ),
        TodoItem(
            id = "10",
            text = "Plan vacation",
            priority = Priority.LOW_PRIORITY,
            startDate = System.currentTimeMillis(),
            isDone = false
        ),
        TodoItem(
            id = "11",
            text = "Chill",
            priority = Priority.URGENT_PRIORITY,
            startDate = System.currentTimeMillis(),
            isDone = false,
            deadline = System.currentTimeMillis() + 10800000
        ),
        TodoItem(
            id = "12",
            text = "Work",
            priority = Priority.LOW_PRIORITY,
            startDate = System.currentTimeMillis(),
            changeDate = System.currentTimeMillis(),
            isDone = true,
            deadline = System.currentTimeMillis() + 10800000
        ),
        TodoItem(
            id = "13",
            text = "Repeat all todo",
            priority = Priority.USUAL_PRIORITY,
            startDate = System.currentTimeMillis(),
            isDone = true
        ),
        TodoItem(
            id = "14",
            text = "Plant trees",
            priority = Priority.URGENT_PRIORITY,
            startDate = System.currentTimeMillis(),
            isDone = false,
            deadline = System.currentTimeMillis() + 7200000
        ),
        TodoItem(
            id = "15",
            text = "Go to the fest",
            priority = Priority.USUAL_PRIORITY,
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