package com.daniil.halushka.todoapp.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.daniil.halushka.todoapp.ui.theme.custom.FontTypography

val typographyScheme = FontTypography(
    title = TextStyle(
        fontFamily = RobotoFamily,
        fontSize = 20.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.5.sp,
        fontWeight = FontWeight.Medium
    ),
    largeTitle = TextStyle(
        fontFamily = RobotoFamily,
        fontSize = 32.sp,
        lineHeight = 38.sp,
        fontWeight = FontWeight.Medium
    ),
    buttonText = TextStyle(
        fontFamily = RobotoFamily,
        fontSize = 14.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.16.sp,
        fontWeight = FontWeight.Medium
    ),
    bodyText = TextStyle(
        fontFamily = RobotoFamily,
        fontSize = 16.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Normal
    ),
    headerText = TextStyle(
        fontFamily = RobotoFamily,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Normal
    )
)