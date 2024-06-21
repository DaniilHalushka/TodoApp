package com.daniil.halushka.todoapp.presentation.screens.elements.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daniil.halushka.todoapp.constants.Priority
import com.daniil.halushka.todoapp.presentation.events.ItemModificationEvent

@Composable
fun DetailsExpandedDropdown(
    expanded: Boolean,
    clickToExpand: (Boolean) -> Unit,
    receiveEvent: (ItemModificationEvent) -> (() -> Unit)
) {
    DropdownMenu(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
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
                receiveEvent = receiveEvent
            )
        }
    }
}

@Composable
fun PriorityItemInDropdown(
    priority: String,
    clickToExpand: (Boolean) -> Unit,
    receiveEvent: (ItemModificationEvent) -> (() -> Unit)
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
            receiveEvent(ItemModificationEvent.UpdatePriority(priority)).invoke()
            clickToExpand(false)
        }
    )
}