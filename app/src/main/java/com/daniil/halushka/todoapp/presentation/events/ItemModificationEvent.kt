package com.daniil.halushka.todoapp.presentation.events

sealed class ItemModificationEvent {
    data object Save : ItemModificationEvent()
    data object Delete : ItemModificationEvent()
    data object Exit : ItemModificationEvent()
    data class UpdateName(val newName: String) : ItemModificationEvent()
    data class UpdatePriority(val newPriority: String) : ItemModificationEvent()
}