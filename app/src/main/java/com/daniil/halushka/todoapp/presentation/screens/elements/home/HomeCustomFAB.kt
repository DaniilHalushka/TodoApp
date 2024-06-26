package com.daniil.halushka.todoapp.presentation.screens.elements.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.daniil.halushka.todoapp.ui.theme.AppTheme

@Composable
fun CustomFAB(
    navigationController: NavController
) {
    FloatingActionButton(
        onClick = {
            navigationController.navigate(
                "Details"
            )
        },
        modifier = Modifier
            .padding(16.dp),
        containerColor = AppTheme.colorScheme.blueColor,
        contentColor = Color.White,
        shape = CircleShape
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null,
        )
    }
}
