package com.daniil.halushka.todoapp.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.daniil.halushka.todoapp.data.models.TodoItem

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

fun TodoItemDatabase.toTodoItem(): TodoItem {
    val (id, text, priority, isDone, startDate, deadline, changeDate) = this

    return TodoItem(
        id = id,
        text = text,
        priority = priority,
        isDone = isDone,
        startDate = startDate,
        deadline = deadline,
        changeDate = changeDate
    )
}