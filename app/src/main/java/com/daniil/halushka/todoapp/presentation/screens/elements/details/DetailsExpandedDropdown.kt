package com.daniil.halushka.todoapp.presentation.screens.elements.details

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daniil.halushka.todoapp.util.constants.Priority
import com.daniil.halushka.todoapp.ui.theme.AppTheme
import com.daniil.halushka.todoapp.ui.theme.TodoAppTheme

/**
 * Composable function to display an expanded dropdown menu for selecting priority.
 *
 * @param expanded Whether the dropdown menu is expanded or not.
 * @param clickToExpand Callback function to toggle the dropdown menu.
 * @param onPrioritySelect Callback function when a priority item is selected.
 */
@Composable
fun DetailsExpandedDropdown(
    expanded: Boolean,
    clickToExpand: (Boolean) -> Unit,
    onPrioritySelect: (String) -> Unit
) {
    DropdownMenu(
        modifier = Modifier
            .background(AppTheme.colorScheme.backElevatedColor)
            .shadow(0.05.dp, RoundedCornerShape(2.dp))
            .clip(RoundedCornerShape(4.dp))
            .widthIn(min = 192.dp),
        expanded = expanded,
        onDismissRequest = { clickToExpand(false) },
        offset = DpOffset(x = 16.dp, y = (-8).dp)
    ) {
        listOf(
            Priority.LOW_PRIORITY,
            Priority.USUAL_PRIORITY,
            Priority.URGENT_PRIORITY
        ).forEach { priority ->
            PriorityItemInDropdown(
                priority = priority,
                clickToExpand = clickToExpand,
                onPrioritySelect = onPrioritySelect
            )
        }
    }
}

@Composable
fun PriorityItemInDropdown(
    priority: String,
    clickToExpand: (Boolean) -> Unit,
    onPrioritySelect: (String) -> Unit
) {
    val priorityColor: Color = when (priority) {
        Priority.URGENT_PRIORITY -> Color.Red
        Priority.USUAL_PRIORITY -> Color.Gray
        Priority.LOW_PRIORITY -> Color.Blue
        else -> Color.Gray
    }

    DropdownMenuItem(
        text = {
            Box(
                modifier = Modifier
                    .heightIn(min = 64.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = priority,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    color = priorityColor
                )
            }
        },
        onClick = {
            clickToExpand(false)
            onPrioritySelect(priority)
        }
    )
}

//Turn on interactive mode
@Composable
@Preview(name = "Light version", showBackground = true)
fun DetailsExpandedDropdownPreview() {
    TodoAppTheme {
        var dropdownClick: Boolean by remember { mutableStateOf(false) }
        var selectedPriority by remember { mutableStateOf(Priority.USUAL_PRIORITY) }
        Row(
            modifier = Modifier
                .background(AppTheme.colorScheme.backPrimaryColor)
        ) {
            DetailsCollapsedDropdown(
                priority = selectedPriority,
                isClicked = { click -> dropdownClick = click }
            )
            DetailsExpandedDropdown(
                expanded = dropdownClick,
                clickToExpand = { click -> dropdownClick = click },
                onPrioritySelect = { newPriority -> selectedPriority = newPriority }
            )
        }
    }
}

@Composable
@Preview(name = "Dark version", uiMode = Configuration.UI_MODE_NIGHT_YES)
fun DetailsExpandedDropdownPreviewDark() {
    TodoAppTheme {
        var dropdownClick: Boolean by remember { mutableStateOf(false) }
        var selectedPriority by remember { mutableStateOf(Priority.USUAL_PRIORITY) }
        Row(
            modifier = Modifier
                .background(AppTheme.colorScheme.backPrimaryColor)
        ) {
            DetailsCollapsedDropdown(
                priority = selectedPriority,
                isClicked = { click -> dropdownClick = click }
            )
            DetailsExpandedDropdown(
                expanded = dropdownClick,
                clickToExpand = { click -> dropdownClick = click },
                onPrioritySelect = { newPriority -> selectedPriority = newPriority }
            )
        }
    }
}