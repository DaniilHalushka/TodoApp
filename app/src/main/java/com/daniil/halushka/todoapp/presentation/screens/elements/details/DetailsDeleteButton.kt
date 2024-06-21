package com.daniil.halushka.todoapp.presentation.screens.elements.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daniil.halushka.todoapp.R

@Composable
fun DetailsDeleteButton(
    clickOnNavigationItem: () -> Unit = {},
    isClicked: Boolean
) {
    val color = if (isClicked) {
        Color.Red
    } else {
        MaterialTheme.colorScheme.onPrimaryContainer
    }

    val activeButtonModifier = Modifier
        .padding(16.dp)
        .clickable(onClick = clickOnNavigationItem)

    Row(
        modifier = activeButtonModifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = stringResource(R.string.delete),
            tint = color
        )

        Text(
            text = stringResource(R.string.delete),
            color = color,
            modifier = Modifier.padding(start = 16.dp),
            fontSize = 16.sp,
        )
    }
}
