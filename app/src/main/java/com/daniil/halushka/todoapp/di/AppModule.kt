package com.daniil.halushka.todoapp.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Dagger Hilt module for application-wide dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule
