package com.daniil.halushka.todoapp.presentation.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.daniil.halushka.todoapp.R
import com.daniil.halushka.todoapp.presentation.viewmodels.SettingsScreenViewModel
import com.daniil.halushka.todoapp.presentation.viewmodels.ThemeSetting

@Composable
fun SettingsScreen(settingsViewModel: SettingsScreenViewModel = hiltViewModel()) {
    val themeSetting by settingsViewModel.themeSetting.collectAsStateWithLifecycle()

    Column(modifier = Modifier.padding(16.dp)) {
        Text(stringResource(R.string.select_theme), style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(8.dp))

        ThemeOption(
            text = stringResource(R.string.light_theme),
            selected = themeSetting == ThemeSetting.LIGHT,
            onClick = { settingsViewModel.setThemeSetting(ThemeSetting.LIGHT) }
        )

        ThemeOption(
            text = stringResource(R.string.dark_theme),
            selected = themeSetting == ThemeSetting.DARK,
            onClick = { settingsViewModel.setThemeSetting(ThemeSetting.DARK) }
        )

        ThemeOption(
            text = stringResource(R.string.system_theme),
            selected = themeSetting == ThemeSetting.SYSTEM,
            onClick = { settingsViewModel.setThemeSetting(ThemeSetting.SYSTEM) }
        )
    }
}

@Composable
fun ThemeOption(text: String, selected: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text, style = MaterialTheme.typography.bodyLarge)
        if (selected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}
