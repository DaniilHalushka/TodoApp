package com.daniil.halushka.todoapp.presentation.screens.elements.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.daniil.halushka.todoapp.presentation.navigation.ScreenRoutes
import com.daniil.halushka.todoapp.ui.theme.AppTheme
import com.daniil.halushka.todoapp.ui.theme.TodoAppTheme

@Composable
fun CustomFAB(
    navigationController: NavController
) {
    FloatingActionButton(
        onClick = {
            navigationController.navigate(
                ScreenRoutes.DetailsScreen.createRoute()
            )
        },
        modifier = Modifier
            .padding(horizontal = 16.dp),
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

@Composable
@Preview(name = "Light version", showBackground = true)
fun FABPreview() {
    TodoAppTheme {
        val navigationController = rememberNavController()
        CustomFAB(navigationController = navigationController)
    }
}

@Composable
@Preview(name = "Dark version", uiMode = Configuration.UI_MODE_NIGHT_YES)
fun FABPreviewDark() {
    TodoAppTheme {
        val navigationController = rememberNavController()
        CustomFAB(navigationController = navigationController)
    }
}
