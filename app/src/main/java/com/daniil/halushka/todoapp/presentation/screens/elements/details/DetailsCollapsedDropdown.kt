package com.daniil.halushka.todoapp.presentation.screens.elements.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daniil.halushka.todoapp.R

@Composable
fun DetailsCollapsedDropdown(
    priority: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { /*TODO make click */ }
    ) {
        Text(
            text = stringResource(R.string.priority),
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 20.sp,
            fontWeight = FontWeight(500)
        )
        Text(
            text = priority,
            color = MaterialTheme.colorScheme.onTertiary,
            fontSize = 16.sp
        )
    }
}