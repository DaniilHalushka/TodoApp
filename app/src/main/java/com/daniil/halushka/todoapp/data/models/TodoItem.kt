package com.daniil.halushka.todoapp.data.models

import com.daniil.halushka.todoapp.data.database.model.TodoItemDatabase

data class TodoItem(
    val id: String,
    val text: String,
    val priority: String,
    val isDone: Boolean,
    val startDate: Long,
    val deadline: Long? = null,
    val changeDate: Long? = null
)

fun TodoItem.toTodoItemDatabase(): TodoItemDatabase {
    val (id, text, priority, isDone, startDate, deadline, changeDate) = this

    return TodoItemDatabase(
        id = id,
        text = text,
        priority = priority,
        isDone = isDone,
        startDate = startDate,
        deadline = deadline,
        changeDate = changeDate
    )
}