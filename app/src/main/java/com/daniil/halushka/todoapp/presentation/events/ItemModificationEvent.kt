package com.daniil.halushka.todoapp.presentation.events

sealed class ItemModificationEvent {
    data class UpdateName(val newName: String) : ItemModificationEvent()
    data class UpdatePriority(val newPriority: String) : ItemModificationEvent()
}