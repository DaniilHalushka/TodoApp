package com.daniil.halushka.todoapp.presentation.navigation

sealed class ScreenRoutes(
    val screenType: String
) {
    data object HomeScreen : ScreenRoutes(
        screenType = "Home"
    )

    data object DetailsScreen : ScreenRoutes(screenType = "Details/{id}") {
        fun createRoute(id: String? = null): String {
            return if (id.isNullOrEmpty()) {
                "Details/new"
            } else {
                "Details/$id"
            }
        }
    }
}