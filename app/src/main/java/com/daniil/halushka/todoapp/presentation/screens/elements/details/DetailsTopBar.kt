package com.daniil.halushka.todoapp.presentation.screens.elements.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daniil.halushka.todoapp.R
import com.daniil.halushka.todoapp.presentation.events.ItemModificationEvent

@Composable
fun DetailsTopBar(
    receiveEvent: (ItemModificationEvent) -> (() -> Unit)
) {
    Box(
        modifier = Modifier.padding(16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = stringResource(R.string.close_details_screen),
                modifier = Modifier
                    .size(24.dp)
                    .clickable(onClick = receiveEvent(ItemModificationEvent.Exit))
            )
            Text(
                text = stringResource(R.string.save_todo),
                modifier = Modifier
                    .clickable(onClick = receiveEvent(ItemModificationEvent.Save)),
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}
