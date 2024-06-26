package com.daniil.halushka.todoapp.presentation.screens.elements.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daniil.halushka.todoapp.R
import com.daniil.halushka.todoapp.ui.theme.AppTheme

@Composable
fun DetailsTopBar(
    clickOnNavigationItem: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Icon(
            modifier = Modifier
                .size(24.dp)
                .clickable(onClick = clickOnNavigationItem),
            imageVector = Icons.Default.Close,
            contentDescription = stringResource(R.string.close_details_screen),
            tint = AppTheme.colorScheme.labelPrimaryColor
        )
        Text(
            modifier = Modifier
                .clickable(onClick = clickOnNavigationItem),
            text = stringResource(R.string.save_todo),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = AppTheme.colorScheme.blueColor
        )

    }
}