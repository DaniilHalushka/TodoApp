package com.daniil.halushka.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.daniil.halushka.todoapp.presentation.navigation.NavigationGraph
import com.daniil.halushka.todoapp.presentation.navigation.ScreenRoutes
import com.daniil.halushka.todoapp.ui.theme.TodoAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoAppTheme {
                TodoApp()
            }
        }
    }
}

@Composable
fun TodoApp() {
    val navigationController = rememberNavController()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp, bottom = 48.dp)
    ) {
        NavigationGraph(
            navController = navigationController,
            startDestination = ScreenRoutes.HomeScreen.screenType
        )
    }

}