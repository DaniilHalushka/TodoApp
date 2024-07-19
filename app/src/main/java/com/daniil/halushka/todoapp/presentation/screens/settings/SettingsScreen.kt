package com.daniil.halushka.todoapp.presentation.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.daniil.halushka.todoapp.R
import com.daniil.halushka.todoapp.presentation.navigation.ScreenRoutes
import com.daniil.halushka.todoapp.presentation.screens.elements.settings.SettingsTopBar
import com.daniil.halushka.todoapp.presentation.viewmodels.SettingsScreenViewModel
import com.daniil.halushka.todoapp.presentation.viewmodels.ThemeSetting
import com.daniil.halushka.todoapp.ui.theme.AppTheme

@Composable
fun SettingsScreen(
    navigationController: NavController,
    settingsViewModel: SettingsScreenViewModel = hiltViewModel()
) {
    val selectedTheme by settingsViewModel.themeSetting.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colorScheme.backPrimaryColor)
            .padding(16.dp)
    ) {
        SettingsTopBar(
            onClick = {
                navigationController.navigate(
                    ScreenRoutes.HomeScreen.screenType
                )
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        ThemeOption(
            text = stringResource(R.string.light_theme),
            selected = selectedTheme == ThemeSetting.LIGHT,
            onClick = { settingsViewModel.setThemeSetting(ThemeSetting.LIGHT) }
        )

        ThemeOption(
            text = stringResource(R.string.dark_theme),
            selected = selectedTheme == ThemeSetting.DARK,
            onClick = { settingsViewModel.setThemeSetting(ThemeSetting.DARK) }
        )

        ThemeOption(
            text = stringResource(R.string.system_theme),
            selected = selectedTheme == ThemeSetting.SYSTEM,
            onClick = { settingsViewModel.setThemeSetting(ThemeSetting.SYSTEM) }
        )

        Text(
            modifier = Modifier
                .padding(top = 16.dp),
            text = stringResource(R.string.to_apply_the_changes_restart_the_application),
            textAlign = TextAlign.Center,
            color = AppTheme.colorScheme.labelPrimaryColor,
            style = AppTheme.typographyScheme.bodyText,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ThemeOption(text: String, selected: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .size(64.dp)
            .clip(RoundedCornerShape(48.dp))
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = text,
            color = AppTheme.colorScheme.labelPrimaryColor,
            style = AppTheme.typographyScheme.bodyText
        )
        if (selected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}
