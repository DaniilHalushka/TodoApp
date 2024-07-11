package com.daniil.halushka.todoapp.presentation.screens.elements.details

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daniil.halushka.todoapp.ui.theme.AppTheme
import com.daniil.halushka.todoapp.ui.theme.TodoAppTheme

/**
 * Composable function to display a horizontal separator line with specified padding and background color.
 */
@Composable
fun DetailsSeparator() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp
            )
            .background(AppTheme.colorScheme.lightGrayColor)
            .height(1.dp)
    )
}

@Composable
@Preview(name = "Light version", showBackground = true)
fun DetailsSeparatorPreview() {
    TodoAppTheme {
        DetailsSeparator()
    }
}

@Composable
@Preview(name = "Dark version", uiMode = Configuration.UI_MODE_NIGHT_YES)
fun DetailsSeparatorPreviewDark() {
    DetailsSeparator()
}