package com.daniil.halushka.todoapp.presentation.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.daniil.halushka.todoapp.presentation.screens.about.AboutScreen
import com.daniil.halushka.todoapp.presentation.screens.details.DetailsScreen
import com.daniil.halushka.todoapp.presentation.screens.home.HomeScreen
import com.daniil.halushka.todoapp.presentation.screens.settings.SettingsScreen

private const val SLIDE_HORIZONTALLY_DURATION = 1500

/**
 * Composable function representing the navigation graph of the application.
 * @param navController NavHostController used for navigating between screens.
 * @param startDestination Start destination of the navigation graph.
 */
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
            ScreenRoutes.HomeScreen.screenType,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -it },
                    animationSpec = tween(SLIDE_HORIZONTALLY_DURATION)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it },
                    animationSpec = tween(SLIDE_HORIZONTALLY_DURATION)
                )
            }
        ) {
            HomeScreen(
                navigationController = navController
            )
        }

        composable(
            route = ScreenRoutes.DetailsScreen.screenType,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(SLIDE_HORIZONTALLY_DURATION)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { it },
                    animationSpec = tween(SLIDE_HORIZONTALLY_DURATION)
                )
            },
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { screen ->
            val arguments = requireNotNull(screen.arguments)
            val todoId = arguments.getString("id")
            DetailsScreen(
                todoId = if (todoId == "new") null else todoId,
                navigationController = navController
            )
        }

        composable(
            ScreenRoutes.SettingsScreen.screenType,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(SLIDE_HORIZONTALLY_DURATION)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { it },
                    animationSpec = tween(SLIDE_HORIZONTALLY_DURATION)
                )
            }
        ) {
            SettingsScreen(
                navigationController = navController
            )
        }

        composable(
            ScreenRoutes.AboutScreen.screenType,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(SLIDE_HORIZONTALLY_DURATION)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { it },
                    animationSpec = tween(SLIDE_HORIZONTALLY_DURATION)
                )
            }
        ) {
            AboutScreen(
                navigationController = navController
            )
        }
    }
}