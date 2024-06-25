package com.daniil.halushka.todoapp.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.asTime(): String {
    val time = Date(this)
    val timeFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    return timeFormat.format(time)
}