package com.daniil.halushka.todoapp.ui.theme.custom

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class ColorPaletteScheme(
    val redColor: Color,
    val greenColor: Color,
    val blueColor: Color,
    val grayColor: Color,
    val lightGrayColor: Color,
    val whiteColor: Color,

    val separatorColor: Color,
    val overlayColor: Color,

    val labelPrimaryColor: Color,
    val labelSecondaryColor: Color,
    val labelTertiaryColor: Color,
    val labelDisableColor: Color,

    val backPrimaryColor: Color,
    val backSecondaryColor: Color,
    val backElevatedColor: Color
)

val LocalColorPaletteScheme = staticCompositionLocalOf {
    ColorPaletteScheme(
        redColor = Color.Unspecified,
        greenColor = Color.Unspecified,
        blueColor = Color.Unspecified,
        grayColor = Color.Unspecified,
        lightGrayColor = Color.Unspecified,
        whiteColor = Color.Unspecified,

        separatorColor = Color.Unspecified,
        overlayColor = Color.Unspecified,

        labelPrimaryColor = Color.Unspecified,
        labelSecondaryColor = Color.Unspecified,
        labelTertiaryColor = Color.Unspecified,
        labelDisableColor = Color.Unspecified,

        backPrimaryColor = Color.Unspecified,
        backSecondaryColor = Color.Unspecified,
        backElevatedColor = Color.Unspecified
    )
}