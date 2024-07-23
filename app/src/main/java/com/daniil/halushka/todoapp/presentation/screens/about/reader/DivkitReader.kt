package com.daniil.halushka.todoapp.presentation.screens.about.reader

import android.content.Context
import org.json.JSONObject

class DivkitReader(private val context: Context) {
    fun readJSON(filename: String): JSONObject {
        val inputStream = context.assets.open(filename)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        return JSONObject(String(buffer, Charsets.UTF_8))
    }
}