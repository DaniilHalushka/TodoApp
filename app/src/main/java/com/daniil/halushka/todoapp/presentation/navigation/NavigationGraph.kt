package com.daniil.halushka.todoapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.daniil.halushka.todoapp.presentation.screens.details.DetailsScreen
import com.daniil.halushka.todoapp.presentation.screens.home.HomeScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            ScreenRoutes.HomeScreen.screenType
        ) {
            HomeScreen(
                navigationController = navController
            )
        }

        composable(
            route = ScreenRoutes.DetailsScreen.screenType,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { screen ->
            val arguments = requireNotNull(screen.arguments)
            val todoId = arguments.getString("id")
            DetailsScreen(
                todoId = if (todoId == "new") null else todoId,
                navigationController = navController
            )
        }
    }
}