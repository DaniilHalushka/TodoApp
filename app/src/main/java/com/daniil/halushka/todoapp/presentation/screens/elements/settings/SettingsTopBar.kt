package com.daniil.halushka.todoapp.presentation.screens.elements.settings

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.daniil.halushka.todoapp.R
import com.daniil.halushka.todoapp.ui.theme.AppTheme

@Composable
fun SettingsTopBar(
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            modifier = Modifier
                .size(24.dp),
            onClick = onClick
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = stringResource(R.string.close_settings_screen),
                tint = AppTheme.colorScheme.labelPrimaryColor
            )
        }

        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(R.string.select_theme),
            color = AppTheme.colorScheme.labelPrimaryColor,
            style = AppTheme.typographyScheme.largeTitle,
            textAlign = TextAlign.Center
        )
    }
}