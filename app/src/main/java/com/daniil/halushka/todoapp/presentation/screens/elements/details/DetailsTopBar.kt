package com.daniil.halushka.todoapp.presentation.screens.elements.details

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daniil.halushka.todoapp.R
import com.daniil.halushka.todoapp.ui.theme.AppTheme
import com.daniil.halushka.todoapp.ui.theme.TodoAppTheme

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
            style = AppTheme.typographyScheme.buttonText,
            color = AppTheme.colorScheme.blueColor
        )

    }
}

@Composable
@Preview(name = "Light version", showBackground = true)
fun DetailsTopBarPreview() {
    TodoAppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colorScheme.backPrimaryColor)
        ) {
            DetailsTopBar()
        }
    }
}

@Composable
@Preview(name = "Dark version", uiMode = Configuration.UI_MODE_NIGHT_YES)
fun DetailsTopBarDark() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colorScheme.backPrimaryColor)
    ) {
        DetailsTopBar()
    }
}