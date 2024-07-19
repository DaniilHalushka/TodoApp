package com.daniil.halushka.todoapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Custom application class for initializing Hilt dependency injection.
 */
@HiltAndroidApp
class HiltTodoApp: Application()