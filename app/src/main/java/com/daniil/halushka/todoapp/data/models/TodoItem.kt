package com.daniil.halushka.todoapp.data.models

data class TodoItem(
    val id: String,
    val text: String,
    val priority: String,
    val isDone: Boolean,
    val startDate: Long,
    val deadline: Long? = null,
    val changeDate: Long? = null
)