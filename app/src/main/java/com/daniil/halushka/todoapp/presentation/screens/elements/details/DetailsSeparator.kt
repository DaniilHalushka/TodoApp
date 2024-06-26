package com.daniil.halushka.todoapp.presentation.screens.elements.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.daniil.halushka.todoapp.ui.theme.AppTheme

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