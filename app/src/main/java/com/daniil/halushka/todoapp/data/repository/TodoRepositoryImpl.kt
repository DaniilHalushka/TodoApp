package com.daniil.halushka.todoapp.data.repository

import com.daniil.halushka.todoapp.constants.NullableTodo
import com.daniil.halushka.todoapp.data.database.di.TodoDao
import com.daniil.halushka.todoapp.data.database.model.toTodoItem
import com.daniil.halushka.todoapp.data.models.TodoItem
import com.daniil.halushka.todoapp.data.models.toTodoItemDatabase
import com.daniil.halushka.todoapp.domain.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TodoRepositoryImpl(private val todoDao: TodoDao) : TodoRepository {

    override suspend fun getTodoList(): List<TodoItem> = withContext(Dispatchers.IO) {
        return@withContext runCatching {
            todoDao.getTodoList().map {
                it.toTodoItem()
            }
        }.getOrElse { emptyList() }
    }

    override suspend fun getUniqueTodo(id: String): TodoItem = withContext(Dispatchers.IO) {
        return@withContext runCatching {
            todoDao.getUniqueTodo(id).toTodoItem()
        }.getOrElse { NullableTodo.nullableModel }
    }

    override suspend fun countFinishedTodo(): Int = withContext(Dispatchers.IO) {
        todoDao.countFinishedTodo()
    }

    override suspend fun addTodoInList(todoItem: TodoItem) = withContext(Dispatchers.IO) {
        todoDao.addTodoInList(todoItem.toTodoItemDatabase())
    }

    override suspend fun saveTodo(todoItem: TodoItem) = withContext(Dispatchers.IO) {
        todoDao.updateTodoItem(todoItem.toTodoItemDatabase())
    }

    override suspend fun updateTodoPriority(id: String, newPriority: String) =
        withContext(Dispatchers.IO) {
            todoDao.updateTodoPriority(id, newPriority)
        }

    override suspend fun updateTodoDeadline(id: String, newDeadline: Long) =
        withContext(Dispatchers.IO) {
            todoDao.updateTodoDeadline(id, newDeadline)
        }

    override suspend fun deleteTodo(id: String) = withContext(Dispatchers.IO) {
        val todoItem = todoDao.getUniqueTodo(id)
        todoDao.deleteTodo(todoItem)
    }

    override suspend fun finishTodo(todoId: String, isTodoDone: Boolean) =
        withContext(Dispatchers.IO) {
            todoDao.finishTodo(todoId, isTodoDone)
        }
}
