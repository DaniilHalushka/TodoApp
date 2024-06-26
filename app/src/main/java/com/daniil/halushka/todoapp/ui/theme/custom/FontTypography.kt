package com.daniil.halushka.todoapp.ui.theme.custom

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle

data class FontTypography(
    val title: TextStyle,
    val largeTitle: TextStyle,
    val buttonText: TextStyle,
    val bodyText: TextStyle,
    val headerText: TextStyle,
)

val LocalAppTypography = staticCompositionLocalOf {
    FontTypography(
        title = TextStyle.Default,
        largeTitle = TextStyle.Default,
        buttonText = TextStyle.Default,
        bodyText = TextStyle.Default,
        headerText = TextStyle.Default,
    )
}