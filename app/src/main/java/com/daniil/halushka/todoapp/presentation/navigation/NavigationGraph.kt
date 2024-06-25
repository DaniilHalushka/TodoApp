package com.daniil.halushka.todoapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
            //todo refactor viewModel in future
            HomeScreen(
                navigationController = navController
            )
        }

        composable(
            ScreenRoutes.DetailsScreen.screenType
        ) {
            //todo add viewModel in future
            DetailsScreen(
                navigationController = navController
            )
        }
    }
}