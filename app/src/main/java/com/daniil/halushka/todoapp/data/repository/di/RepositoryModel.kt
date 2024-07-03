package com.daniil.halushka.todoapp.data.repository.di

import com.daniil.halushka.todoapp.data.repository.TodoRepository
import com.daniil.halushka.todoapp.domain.repository.TodoRepositoryInterface
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
    fun provideTodoRepository(): TodoRepositoryInterface {
        return TodoRepository()
    }
}