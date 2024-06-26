package com.daniil.halushka.todoapp.presentation.screens.elements.home

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
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daniil.halushka.todoapp.R
import com.daniil.halushka.todoapp.ui.theme.AppTheme

@Composable
fun HomeTopBar(
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
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = AppTheme.colorScheme.labelPrimaryColor
                    )
                    Text(
                        text = stringResource(
                            id = R.string.completed_todo_s,
                            completedItemsCount
                        ),
                        fontSize = 20.sp,
                        color = AppTheme.colorScheme.labelSecondaryColor
                    )
                }
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
fun EyeIcon(
    onIconClick: () -> Unit,
    isDone: Boolean
) {
    Crossfade(
        modifier = Modifier
            .size(24.dp),
        targetState = isDone,
        animationSpec = tween(200),
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