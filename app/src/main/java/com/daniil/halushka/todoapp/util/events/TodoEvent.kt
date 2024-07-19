package com.daniil.halushka.todoapp.util.events

sealed class TodoEvent {
    data object TodoListUpdated : TodoEvent()
}