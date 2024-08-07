package com.daniil.halushka.todoapp.presentation.screens.elements.home

import android.content.res.Configuration
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.daniil.halushka.todoapp.R
import com.daniil.halushka.todoapp.presentation.navigation.ScreenRoutes
import com.daniil.halushka.todoapp.ui.theme.AppTheme
import com.daniil.halushka.todoapp.ui.theme.TodoAppTheme

/**
 * Composable function that represents the top bar of the home screen.
 *
 * @param completedItemsCount The number of completed items to display in the top bar.
 * @param onEyeIconClick Callback invoked when the eye icon is clicked to toggle visibility of completed tasks.
 * @param showFinished Boolean state indicating whether completed tasks are currently shown or hidden.
 * @param height Height of the top bar.
 */
@Composable
fun HomeTopBar(
    navigationController: NavController,
    completedItemsCount: Int,
    onEyeIconClick: (Boolean) -> Unit,
    showFinished: Boolean,
    height: Dp
) {
    var localShowState by remember { mutableStateOf(showFinished) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .background(AppTheme.colorScheme.backPrimaryColor)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            shadowElevation = 4.dp,
        ) {
            Row(
                modifier = Modifier
                    .background(AppTheme.colorScheme.backPrimaryColor)
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f),
                ) {
                    Text(
                        text = stringResource(R.string.my_tasks_todo_s),
                        color = AppTheme.colorScheme.labelPrimaryColor,
                        style = AppTheme.typographyScheme.largeTitle
                    )
                    Text(
                        text = stringResource(
                            id = R.string.completed_todo_s,
                            completedItemsCount
                        ),
                        color = AppTheme.colorScheme.labelSecondaryColor,
                        style = AppTheme.typographyScheme.bodyText
                    )
                }
                SettingsIcon(
                    onIconClick = {
                        navigationController.navigate(
                            ScreenRoutes.SettingsScreen.screenType
                        )
                    }
                )
                AboutIcon(
                    onIconClick = {
                        navigationController.navigate(
                            ScreenRoutes.AboutScreen.screenType
                        )
                    }
                )
                EyeIcon(
                    onIconClick = {
                        localShowState = !localShowState
                        onEyeIconClick.invoke(localShowState)
                    },
                    isDone = localShowState
                )
            }
        }
    }
}

@Composable
fun AboutIcon(
    onIconClick: () -> Unit
) {
    IconButton(
        modifier = Modifier
            .padding(8.dp)
            .size(24.dp),
        onClick =  onIconClick
    ) {
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = stringResource(R.string.button_to_go_on_about_screen),
            tint = AppTheme.colorScheme.blueColor
        )
    }
}

@Composable
fun SettingsIcon(
    onIconClick: () -> Unit
) {
    IconButton(
        modifier = Modifier
            .size(24.dp),
        onClick =  onIconClick
    ) {
        Icon(
            imageVector = Icons.Default.Settings,
            contentDescription = stringResource(R.string.button_to_go_on_settings_screen),
            tint = AppTheme.colorScheme.blueColor
        )
    }
}


private const val DURATION = 200

/**
 * Composable function that displays an icon to toggle visibility of completed tasks.
 *
 * @param onIconClick Callback invoked when the eye icon is clicked to toggle visibility.
 * @param isDone Boolean state indicating whether completed tasks are currently shown or hidden.
 */
@Composable
fun EyeIcon(
    onIconClick: () -> Unit,
    isDone: Boolean
) {
    Crossfade(
        modifier = Modifier
            .size(24.dp),
        targetState = isDone,
        animationSpec = tween(DURATION),
        label = stringResource(R.string.visibility_icon)
    ) { iconState ->
        Icon(
            modifier = Modifier
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { onIconClick.invoke() },
            painter = painterResource(
                if (iconState)
                    R.drawable.ic_visibility
                else R.drawable.ic_visibility_off
            ),
            contentDescription = stringResource(
                if (iconState)
                    R.string.hide_completed_tasks
                else R.string.show_completed_tasks
            ),
            tint = AppTheme.colorScheme.blueColor
        )
    }
}

@Composable
@Preview(name = "Light version", showBackground = true)
fun HomeTopBarPreview() {
    TodoAppTheme {
        val navigationController = rememberNavController()
        HomeTopBar(
            navigationController = navigationController,
            completedItemsCount = 10,
            onEyeIconClick = {},
            showFinished = true,
            height = 100.dp
        )
    }
}

@Composable
@Preview(name = "Dark version", uiMode = Configuration.UI_MODE_NIGHT_YES)
fun HomeTopBarPreviewDark() {
    TodoAppTheme {
        val navigationController = rememberNavController()
        HomeTopBar(
            navigationController = navigationController,
            completedItemsCount = 10,
            onEyeIconClick = {},
            showFinished = true,
            height = 100.dp
        )
    }
}