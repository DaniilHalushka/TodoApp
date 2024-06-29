package com.daniil.halushka.todoapp.data.repository

import androidx.compose.runtime.mutableStateListOf
import com.daniil.halushka.todoapp.constants.Priority
import com.daniil.halushka.todoapp.data.models.TodoItem
import com.daniil.halushka.todoapp.domain.repository.TodoRepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TodoRepository : TodoRepositoryInterface {
    private val todoList = mutableStateListOf(
        TodoItem(
            id = "1",
            text = "Create TodoItem",
            priority = Priority.LOW_PRIORITY,
            startDate = System.currentTimeMillis(),
            isDone = false
        ),
        TodoItem(
            id = "2",
            text = "Create TodoRepository",
            priority = Priority.USUAL_PRIORITY,
            startDate = System.currentTimeMillis(),
            isDone = false,
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
            isDone = false
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
            isDone = false,
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
            isDone = false,
            deadline = System.currentTimeMillis() + 10800000
        ),
        TodoItem(
            id = "13",
            text = "Repeat all todo",
            priority = Priority.USUAL_PRIORITY,
            startDate = System.currentTimeMillis(),
            isDone = false
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

    override suspend fun getTodoList(): List<TodoItem> = withContext(Dispatchers.IO) {
        todoList
    }

    override suspend fun getUniqueTodo(id: String) = withContext(Dispatchers.IO) {
        todoList.first { todoItem -> todoItem.id == id }
    }

    override suspend fun addTodoInList(todoItem: TodoItem): Unit = withContext(Dispatchers.IO) {
        todoList.add(todoItem)
    }

    override suspend fun saveTodo(todoItem: TodoItem): Unit = withContext(Dispatchers.IO) {
        val index = todoList.indexOfFirst { it.id == todoItem.id }
        if (index == -1) {
            val newId = System.currentTimeMillis().toString()

            val newItem = todoItem.copy(id = newId)
            todoList.add(newItem)
        } else {
            todoList[index] = todoItem
        }
    }

    override suspend fun deleteTodo(id: String): Unit = withContext(Dispatchers.IO) {
        todoList.removeAll { todo -> todo.id == id }
    }

    override suspend fun countFinishedTodo(): Int = withContext(Dispatchers.IO) {
        todoList.count { it.isDone }
    }

    override suspend fun finishTodo(todoId: String, isTodoDone: Boolean): Unit =
        withContext(Dispatchers.IO) {
            todoList.indexOfFirst { todo -> todo.id == todoId }
                .takeIf { result -> result != -1 }?.let { index ->
                    val updatedTodo = todoList[index].copy(isDone = isTodoDone)
                    todoList[index] = updatedTodo
                }
        }
}