package com.daniil.halushka.todoapp.util.constants

import com.daniil.halushka.todoapp.data.models.TodoItem

/**
 * Object providing a default nullable [TodoItem] instance.
 */
object NullableTodo {
    val nullableModel = TodoItem(
        id = "",
        text = "",
        priority = Priority.USUAL_PRIORITY,
        deadline = null,
        isDone = false,
        startDate = System.currentTimeMillis(),
        changeDate = null
    )
}