package com.daniil.halushka.todoapp.util

import android.content.Context
import java.util.Properties

object PropertiesUtil {
    fun getProperty(context: Context, propertyName: String): String? {
        val properties = Properties()
        val assetManager = context.assets
        val inputStream = assetManager.open("local.properties")
        properties.load(inputStream)
        return properties.getProperty(propertyName)
    }
}
