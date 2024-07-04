package com.daniil.halushka.todoapp.constants

object Priority {
    const val LOW_PRIORITY = "Low"
    const val USUAL_PRIORITY = "Usual"
    const val URGENT_PRIORITY = "!! Urgent"

    fun convertPriorityToImportance(priority: Priority): String {
        return when (priority.toString()) {
            LOW_PRIORITY -> {
                "low"
            }

            USUAL_PRIORITY -> {
                "basic"
            }

            URGENT_PRIORITY -> {
                "important"
            }

            else -> {""}
        }
    }
}