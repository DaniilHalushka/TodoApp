package com.daniil.halushka.todoapp.constants

import com.daniil.halushka.todoapp.data.models.TodoItem

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