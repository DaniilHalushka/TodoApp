package com.daniil.halushka.todoapp.data.database.di

import android.app.Application
import androidx.room.Room
import com.daniil.halushka.todoapp.data.database.TodoDatabase
import com.daniil.halushka.todoapp.data.repository.TodoRepositoryImpl
import com.daniil.halushka.todoapp.domain.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger Hilt module for providing dependencies related to Room database.
 */
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

    @Provides
    @Singleton
    fun provideRepository(
        database: TodoDatabase
    ): TodoRepository {
        return TodoRepositoryImpl(database)
    }
}