package com.daniil.halushka.todoapp.presentation.viewmodels

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsScreenViewModel @Inject constructor(
    @ApplicationContext context: Context
) : ViewModel() {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)

    private val _themeSetting = MutableStateFlow(loadThemeSetting())
    val themeSetting: StateFlow<ThemeSetting> get() = _themeSetting

    private fun loadThemeSetting(): ThemeSetting {
        val themeValue = sharedPreferences.getInt("theme_setting", ThemeSetting.SYSTEM.ordinal)
        return ThemeSetting.entries[themeValue]
    }

    fun setThemeSetting(themeSetting: ThemeSetting) {
        viewModelScope.launch {
            _themeSetting.value = themeSetting
            sharedPreferences.edit().putInt("theme_setting", themeSetting.ordinal).apply()
        }
    }
}

enum class ThemeSetting {
    LIGHT,
    DARK,
    SYSTEM
}