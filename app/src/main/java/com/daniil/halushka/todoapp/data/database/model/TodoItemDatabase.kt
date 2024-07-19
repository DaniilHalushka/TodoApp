package com.daniil.halushka.todoapp.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.daniil.halushka.todoapp.data.models.TodoItem

/**
 * Entity class representing a todoItem in the Room database.
 *
 * @property id Unique identifier of the todoItem.
 * @property text Text content of the todoItem.
 * @property priority Priority level of the todoItem.
 * @property isDone Flag indicating if the todoItem is completed.
 * @property startDate Timestamp when the todoItem was created.
 * @property deadline Optional deadline timestamp for the todoItem.
 * @property changeDate Optional timestamp indicating the last modification of the todoItem.
 */
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

/**
 * Extension function to convert a [TodoItemDatabase] entity to a [TodoItem] domain model.
 */
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