package com.daniil.halushka.todoapp.presentation.screens.elements.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.daniil.halushka.todoapp.R
import com.daniil.halushka.todoapp.ui.theme.AppTheme

@Composable
fun DetailsCollapsedDropdown(
    priority: String,
    isClicked: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { isClicked(true) }
    ) {
        Text(
            text = stringResource(R.string.priority),
            color = AppTheme.colorScheme.labelPrimaryColor,
            style = AppTheme.typographyScheme.title
        )
        Text(
            text = priority,
            color = AppTheme.colorScheme.labelTertiaryColor,
            style = AppTheme.typographyScheme.bodyText
        )
    }
}