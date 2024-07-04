package com.daniil.halushka.todoapp.constants

object Priority {
    const val LOW_PRIORITY = "Low"
    const val USUAL_PRIORITY = "Usual"
    const val URGENT_PRIORITY = "!! Urgent"

    fun convertPriorityToImportance(priority: String): String {
        return when (priority) {
            LOW_PRIORITY -> {
                "low"
            }

            USUAL_PRIORITY -> {
                "basic"
            }

            URGENT_PRIORITY -> {
                "important"
            }

            else -> {
                ""
            }
        }
    }

    fun convertImportanceToPriority(importance: String): String {
        var priority = ""
        when (importance) {
            "low" -> {
                priority = LOW_PRIORITY
            }

            "basic" -> {
                priority = USUAL_PRIORITY
            }

            "important" -> {
                priority = URGENT_PRIORITY
            }

        }
        return priority
    }
}