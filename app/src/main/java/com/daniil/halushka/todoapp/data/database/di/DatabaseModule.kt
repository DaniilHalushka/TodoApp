package com.daniil.halushka.todoapp.data.database.di

import android.app.Application
import androidx.room.Room
import com.daniil.halushka.todoapp.data.database.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideTodoDatabase(
        application: Application
    ): TodoDatabase {
        return Room.databaseBuilder(
            application,
            TodoDatabase::class.java,
            "todo_database.db"
        ).build()
    }
}