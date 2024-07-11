package com.daniil.halushka.todoapp.data.database.di

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.daniil.halushka.todoapp.data.database.model.TodoItemDatabase

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

    @Update
    suspend fun updateTodoItem(todoItem: TodoItemDatabase)

    @Query("UPDATE todo_items SET priority = :newPriority WHERE id = :id")
    suspend fun updateTodoPriority(id: String, newPriority: String)

    @Query("UPDATE todo_items SET deadline = :newDeadline WHERE id = :id")
    suspend fun updateTodoDeadline(id: String, newDeadline: Long)

    @Delete
    suspend fun deleteTodo(todoItem: TodoItemDatabase)

    @Query("UPDATE todo_items SET isDone = :isTodoDone WHERE id = :id")
    suspend fun finishTodo(id: String, isTodoDone: Boolean)
}
