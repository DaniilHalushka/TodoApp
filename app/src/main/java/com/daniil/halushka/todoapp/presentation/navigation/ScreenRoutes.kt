package com.daniil.halushka.todoapp.presentation.navigation

sealed class ScreenRoutes(
    val screenType: String
) {
    data object HomeScreen : ScreenRoutes(
        screenType = "Home"
    )

    data object DetailsScreen : ScreenRoutes(
        screenType = "Details/{id}"
    )
}