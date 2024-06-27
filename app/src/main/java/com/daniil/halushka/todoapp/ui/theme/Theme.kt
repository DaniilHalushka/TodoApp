package com.daniil.halushka.todoapp.ui.theme

import android.app.Activity
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

@Composable
fun PreviewTodoAppTheme() {
    Column {
        Text(
            "redColor",
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.colorScheme.redColor)
                .padding(8.dp)
        )
        Text(
            "greenColor",
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.colorScheme.greenColor)
                .padding(8.dp)
        )
        Text(
            "blueColor",
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.colorScheme.blueColor)
                .padding(8.dp)
        )
        Text(
            "grayColor",
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.colorScheme.grayColor)
                .padding(8.dp)
        )
        Text(
            "lightGrayColor",
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.colorScheme.lightGrayColor)
                .padding(8.dp)
        )
        Text(
            "White",
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.colorScheme.whiteColor)
                .padding(8.dp)
        )
        Text(
            "separatorColor",
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.colorScheme.separatorColor)
                .padding(8.dp)
        )
        Text(
            "overlayColor",
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.colorScheme.overlayColor)
                .padding(8.dp)
        )
        Text(
            "labelPrimaryColor",
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.colorScheme.labelPrimaryColor)
                .padding(8.dp),
            color = AppTheme.colorScheme.redColor
        )
        Text(
            "labelSecondaryColor",
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.colorScheme.labelSecondaryColor)
                .padding(8.dp)
        )
        Text(
            "labelTertiaryColor",
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.colorScheme.labelTertiaryColor)
                .padding(8.dp)
        )
        Text(
            "labelDisableColor",
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.colorScheme.labelDisableColor)
                .padding(8.dp)
        )
        Text(
            "backPrimaryColor",
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.colorScheme.backPrimaryColor)
                .padding(8.dp),
        )
        Text(
            "backSecondaryColor",
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.colorScheme.backSecondaryColor)
                .padding(8.dp),
        )
        Text(
            "backElevatedColor",
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.colorScheme.backElevatedColor)
                .padding(8.dp),
        )
    }
}


@Composable
@Preview(name = "Light version")
fun ThemePreviewLight() {
    TodoAppTheme {
        PreviewTodoAppTheme()
    }
}

@Composable
@Preview(name = "Dark version", uiMode = Configuration.UI_MODE_NIGHT_YES)
fun ThemePreviewDark() {
    TodoAppTheme {
        PreviewTodoAppTheme()
    }
}