package com.daniil.halushka.todoapp.data.repository.di

import com.daniil.halushka.todoapp.data.repository.TodoRepositoryImpl
import com.daniil.halushka.todoapp.domain.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModel {
    @Provides
    @Singleton
    fun provideTodoRepository(): TodoRepository {
        return TodoRepositoryImpl()
    }
}