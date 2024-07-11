package com.daniil.halushka.todoapp.data.models

import com.daniil.halushka.todoapp.data.database.model.TodoItemDatabase

/**
 * Data class representing a todoItem in the domain layer of the application.
 *
 * @property id Unique identifier of the todoItem.
 * @property text Text content of the todoItem.
 * @property priority Priority level of the todoItem.
 * @property isDone Flag indicating if the todoItem is completed.
 * @property startDate Timestamp when the todoItem was created.
 * @property deadline Optional deadline timestamp for the todoItem.
 * @property changeDate Optional timestamp indicating the last modification of the todoItem.
 */
data class TodoItem(
    val id: String,
    val text: String,
    val priority: String,
    val isDone: Boolean,
    val startDate: Long,
    val deadline: Long? = null,
    val changeDate: Long? = null
)

/**
 * Extension function to convert a [TodoItem] domain model to a [TodoItemDatabase] entity.
 */
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