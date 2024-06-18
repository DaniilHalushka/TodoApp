package com.daniil.halushka.todoapp.presentation.screens.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.daniil.halushka.todoapp.R
import com.daniil.halushka.todoapp.constants.Constants

@Composable
fun CustomCheckbox(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    priority: String
) {
    Box(
        modifier = Modifier
            .size(24.dp)
            .border(1.dp, color = MaterialTheme.colorScheme.primaryContainer)
            .background(getCheckboxColor(isChecked = isChecked, priority = priority))
            .clickable { onCheckedChange(!isChecked) },
        contentAlignment = Alignment.Center
    ) {
        when {
            isChecked -> {
                Icon(
                    painter = painterResource(id = R.drawable.ic_done_checkbox),
                    contentDescription = stringResource(
                        R.string.done_todo
                    )
                )
            }

            priority == Constants.URGENT_PRIORITY -> Icon(
                painter = painterResource(id = R.drawable.ic_urgent_undone_checkbox),
                contentDescription = stringResource(
                    R.string.important_undone_todo
                )
            )

            else -> Icon(
                painter = painterResource(id = R.drawable.ic_normal_undone_checkbox),
                contentDescription = stringResource(
                    R.string.normal_undone_todo
                )
            )
        }
    }
}

@Composable
fun getCheckboxColor(
    isChecked: Boolean,
    priority: String
): Color {
    return when {
        isChecked -> Color.Green
        priority == Constants.URGENT_PRIORITY -> Color.Red
        else -> Color.Gray
    }
}