package com.daniil.halushka.todoapp.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = nightBackPrimaryColor,
    secondary = nightBackSecondaryColor,
    tertiary = blueColor,
    background = nightBackPrimaryColor,
    surface = nightBackElevatedColor,
    onPrimary = nightLabelPrimaryColor,
    onSecondary = nightLabelSecondaryColor,
    onTertiary = nightLabelTertiaryColor,
    onBackground = nightLabelPrimaryColor,
    onSurface = nightLabelPrimaryColor,

    primaryContainer = nightSeparatorColor,
    secondaryContainer = nightOverlayColor,
    onPrimaryContainer = nightLabelDisableColor
)

private val LightColorScheme = lightColorScheme(
    primary = backPrimaryColor,
    secondary = backSecondaryColor,
    tertiary = blueColor,
    background = backPrimaryColor,
    surface = backElevatedColor,
    onPrimary = labelPrimaryColor,
    onSecondary = labelSecondaryColor,
    onTertiary = labelTertiaryColor,
    onBackground = labelPrimaryColor,
    onSurface = labelPrimaryColor,

    primaryContainer = separatorColor,
    secondaryContainer = overlayColor,
    onPrimaryContainer = labelDisableColor
)

@Composable
fun TodoAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            window.navigationBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}