package com.daniil.halushka.todoapp.data.network.repository

import android.content.Context
import android.content.SharedPreferences
import com.daniil.halushka.todoapp.util.PropertiesUtil

class SharedPreferencesRepository(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("todo_app_prefs", Context.MODE_PRIVATE)
    private val context = context.applicationContext

    companion object {
        private const val KEY_AUTH_TOKEN = "auth_token"
        private const val KEY_REVISION = "revision"
    }

    fun getAuthToken(): String? {
        return sharedPreferences.getString(KEY_AUTH_TOKEN, null)
            ?: PropertiesUtil.getProperty(context, "auth.token")
    }

    fun setAuthToken(token: String) {
        sharedPreferences.edit().putString(KEY_AUTH_TOKEN, token).apply()
    }

    fun getRevision(): Int {
        return sharedPreferences.getInt(KEY_REVISION, 0)
    }

    fun setRevision(revision: Int) {
        sharedPreferences.edit().putInt(KEY_REVISION, revision).apply()
    }
}
