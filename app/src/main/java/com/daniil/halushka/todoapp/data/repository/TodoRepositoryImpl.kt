package com.daniil.halushka.todoapp.data.repository

import com.daniil.halushka.todoapp.constants.NullableTodo
import com.daniil.halushka.todoapp.data.database.TodoDatabase
import com.daniil.halushka.todoapp.data.database.model.toTodoItem
import com.daniil.halushka.todoapp.data.models.TodoItem
import com.daniil.halushka.todoapp.data.models.toTodoItemDatabase
import com.daniil.halushka.todoapp.domain.repository.TodoRepository
import com.daniil.halushka.todoapp.util.generateUniqueId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TodoRepositoryImpl(private val database: TodoDatabase) : TodoRepository {

    override suspend fun getTodoList(): List<TodoItem> = withContext(Dispatchers.IO) {
        return@withContext runCatching {
            database.todoDao.getTodoList().map {
                it.toTodoItem()
            }
        }.getOrElse { emptyList() }
    }

    override suspend fun getUniqueTodo(id: String): TodoItem = withContext(Dispatchers.IO) {
        return@withContext runCatching {
            database.todoDao.getUniqueTodo(id).toTodoItem()
        }.getOrElse { NullableTodo.nullableModel }
    }

    override suspend fun countFinishedTodo(): Int = withContext(Dispatchers.IO) {
        database.todoDao.countFinishedTodo()
    }

    override suspend fun addTodoInList(todoItem: TodoItem) = withContext(Dispatchers.IO) {
        val todoWithId = if (todoItem.id.isBlank()) {
            todoItem.copy(id = generateUniqueId())
        } else {
            todoItem
        }
        database.todoDao.addTodoInList(todoWithId.toTodoItemDatabase())
    }


    override suspend fun updateTodoItem(todoItem: TodoItem) = withContext(Dispatchers.IO) {
        database.todoDao.updateTodoItem(todoItem.toTodoItemDatabase())
    }

    override suspend fun deleteTodo(id: String) = withContext(Dispatchers.IO) {
        val todoItem = database.todoDao.getUniqueTodo(id)
        database.todoDao.deleteTodo(todoItem)
    }

    override suspend fun finishTodo(todoId: String, isTodoDone: Boolean) =
        withContext(Dispatchers.IO) {
            database.todoDao.finishTodo(todoId, isTodoDone)
        }
}
