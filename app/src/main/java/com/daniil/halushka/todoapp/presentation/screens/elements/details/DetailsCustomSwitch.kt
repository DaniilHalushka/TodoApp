package com.daniil.halushka.todoapp.presentation.screens.elements.details

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp

@Composable
fun DetailsCustomSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    val offset by animateDpAsState(targetValue = if (checked) 18.dp else 0.dp, label = "")

    val circleBackgroundColor = if (checked) MaterialTheme.colorScheme.tertiary
        else MaterialTheme.colorScheme.surface

    val backgroundContainerColor = if (checked) MaterialTheme.colorScheme.tertiary
        else MaterialTheme.colorScheme.secondaryContainer

    Box(
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(backgroundContainerColor)
                //TODO click
                .clickable { onCheckedChange(!checked) }
                .padding(horizontal = 4.dp, vertical = 2.dp)
                .size(width = 32.dp, height = 12.dp)
        )

        Box(
            modifier = Modifier
                .offset(x = offset)
                .shadow(2.dp, CircleShape)
                .size(22.dp)
                .clip(CircleShape)
                .background(circleBackgroundColor)
        )
    }
}