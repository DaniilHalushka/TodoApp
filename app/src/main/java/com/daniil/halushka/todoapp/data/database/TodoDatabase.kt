package com.daniil.halushka.todoapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.daniil.halushka.todoapp.data.database.di.TodoDao
import com.daniil.halushka.todoapp.data.database.model.TodoItemDatabase

@Database(entities = [TodoItemDatabase::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}
