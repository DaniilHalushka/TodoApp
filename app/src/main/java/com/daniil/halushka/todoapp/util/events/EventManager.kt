package com.daniil.halushka.todoapp.util.events

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object EventManager {
    private val _events = MutableSharedFlow<TodoEvent>()
    val events = _events.asSharedFlow()

    suspend fun sendEvent(event: TodoEvent) {
        _events.emit(event)
    }
}