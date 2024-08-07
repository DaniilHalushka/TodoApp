package com.daniil.halushka.todoapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.daniil.halushka.todoapp.data.database.di.TodoDao
import com.daniil.halushka.todoapp.data.database.model.TodoItemDatabase

/**
 * Room database class for storing todo items.
 */
@Database(entities = [TodoItemDatabase::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract val todoDao: TodoDao
}
