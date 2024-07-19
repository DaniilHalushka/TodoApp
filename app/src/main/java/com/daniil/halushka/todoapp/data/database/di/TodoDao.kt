package com.daniil.halushka.todoapp.data.database.di

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.daniil.halushka.todoapp.data.database.model.TodoItemDatabase

/**
 * Data Access Object (DAO) for interacting with TodoItemDatabase entities in Room database.
 */
@Dao
interface TodoDao {

    @Query("SELECT * FROM todo_items")
    suspend fun getTodoList(): List<TodoItemDatabase>

    @Query("SELECT * FROM todo_items WHERE id = :id")
    suspend fun getUniqueTodo(id: String): TodoItemDatabase

    @Query("SELECT COUNT(*) FROM todo_items WHERE isDone = 1")
    suspend fun countFinishedTodo(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTodoInList(todoItem: TodoItemDatabase)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTodoItem(todoItem: TodoItemDatabase)

    @Delete
    suspend fun deleteTodo(todoItem: TodoItemDatabase)

    @Query("UPDATE todo_items SET isDone = :isTodoDone WHERE id = :id")
    suspend fun finishTodo(id: String, isTodoDone: Boolean)
}

