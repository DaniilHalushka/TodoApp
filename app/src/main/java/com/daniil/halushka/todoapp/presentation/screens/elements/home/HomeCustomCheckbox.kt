package com.daniil.halushka.todoapp.presentation.screens.elements.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.daniil.halushka.todoapp.constants.Priority
import com.daniil.halushka.todoapp.ui.theme.AppTheme

@Composable
fun CustomCheckbox(
    isChecked: Boolean,
    priority: String,
    modifier: Modifier = Modifier,
    size: Float = 24f,
    onValueChange: (Boolean) -> Unit
) {
    val priorityColor: Color = when {
        isChecked -> Color.Green
        priority == Priority.URGENT_PRIORITY -> Color.Red
        else -> Color.Gray
    }

    val checkboxColor: Color by animateColorAsState(
        targetValue = if (isChecked) Color.Green else Color.White,
        animationSpec = tween(durationMillis = 200),
        label = ""
    )

    val interactionSource = remember { MutableInteractionSource() }
    val density = LocalDensity.current
    val duration = 200

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .toggleable(
                value = isChecked,
                role = Role.Checkbox,
                onValueChange = onValueChange,
                indication = rememberRipple(
                    bounded = true,
                    radius = size.dp / 2,
                    color = priorityColor
                ),
                interactionSource = interactionSource
            )
    ) {
        Box(
            modifier = Modifier
                .size(size.dp)
                .background(color = checkboxColor, shape = RoundedCornerShape(4.dp))
                .border(width = 2.5.dp, color = priorityColor, shape = RoundedCornerShape(4.dp)),
            contentAlignment = Alignment.Center
        ) {

            androidx.compose.animation.AnimatedVisibility(
                visible = isChecked,
                enter = slideInHorizontally(animationSpec = tween(durationMillis = duration)) {
                    with(density) { (size * -0.5).dp.roundToPx() }
                } + expandHorizontally(
                    expandFrom = Alignment.Start,
                    animationSpec = tween(durationMillis = duration)
                ),
                exit = fadeOut(animationSpec = tween(durationMillis = duration))
            ) {
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = onValueChange,
                    modifier = Modifier.size(size.dp),
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color.Transparent,
                        uncheckedColor = Color.Transparent,
                        checkmarkColor = AppTheme.colorScheme.backPrimaryColor
                    )
                )
            }
        }
    }
}
