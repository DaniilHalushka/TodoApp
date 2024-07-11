package com.daniil.halushka.todoapp.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_items")
data class TodoItemDatabase(
    @PrimaryKey val id: String,
    val text: String,
    val priority: String,
    val isDone: Boolean,
    val startDate: Long,
    val deadline: Long? = null,
    val changeDate: Long? = null
)
