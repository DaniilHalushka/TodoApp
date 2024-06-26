package com.daniil.halushka.todoapp.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.daniil.halushka.todoapp.ui.theme.custom.ColorPaletteScheme
import com.daniil.halushka.todoapp.ui.theme.custom.FontTypography
import com.daniil.halushka.todoapp.ui.theme.custom.LocalAppTypography
import com.daniil.halushka.todoapp.ui.theme.custom.LocalColorPaletteScheme

object AppTheme {
    val colorScheme: ColorPaletteScheme
        @Composable get() = LocalColorPaletteScheme.current

    val typographyScheme: FontTypography
        @Composable get() = LocalAppTypography.current
}

private val DarkColorScheme = ColorPaletteScheme(
    redColor = redColor,
    greenColor = greenColor,
    blueColor = blueColor,
    grayColor = grayColor,
    lightGrayColor = lightGrayColor,
    whiteColor = whiteColor,

    separatorColor = nightSeparatorColor,
    overlayColor = nightOverlayColor,

    labelPrimaryColor = nightLabelPrimaryColor,
    labelSecondaryColor = nightLabelSecondaryColor,
    labelTertiaryColor = nightLabelTertiaryColor,
    labelDisableColor = nightLabelDisableColor,

    backPrimaryColor = nightBackPrimaryColor,
    backSecondaryColor = nightBackSecondaryColor,
    backElevatedColor = nightBackElevatedColor
)

private val LightColorScheme = ColorPaletteScheme(
    redColor = redColor,
    greenColor = greenColor,
    blueColor = blueColor,
    grayColor = grayColor,
    lightGrayColor = lightGrayColor,
    whiteColor = whiteColor,

    separatorColor = separatorColor,
    overlayColor = overlayColor,

    labelPrimaryColor = labelPrimaryColor,
    labelSecondaryColor = labelSecondaryColor,
    labelTertiaryColor = labelTertiaryColor,
    labelDisableColor = labelDisableColor,

    backPrimaryColor = backPrimaryColor,
    backSecondaryColor = backSecondaryColor,
    backElevatedColor = backElevatedColor
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
            window.statusBarColor = colorScheme.backPrimaryColor.toArgb()
            window.navigationBarColor = colorScheme.backPrimaryColor.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    CompositionLocalProvider(
        LocalColorPaletteScheme provides colorScheme,
        LocalAppTypography provides typographyScheme,
        content = content
    )
}