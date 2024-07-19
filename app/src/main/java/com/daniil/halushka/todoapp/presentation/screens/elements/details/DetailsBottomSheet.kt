package com.daniil.halushka.todoapp.presentation.screens.elements.details

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daniil.halushka.todoapp.R
import com.daniil.halushka.todoapp.ui.theme.AppTheme
import com.daniil.halushka.todoapp.util.constants.Priority
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PriorityBottomSheet(
    showBottomSheet: Boolean,
    onDismissRequest: () -> Unit,
    selectedPriority: String,
    onPrioritySelect: (String) -> Unit
) {
    val bottomSheetState = rememberModalBottomSheetState(false)
    val scope = rememberCoroutineScope()
    val currentOnPrioritySelect by rememberUpdatedState(onPrioritySelect)

    LaunchedEffect(showBottomSheet) {
        if (showBottomSheet) {
            bottomSheetState.show()
        } else {
            bottomSheetState.hide()
        }
    }

    ModalBottomSheet(
        onDismissRequest = { onDismissRequest() },
        sheetState = bottomSheetState,
        containerColor = AppTheme.colorScheme.backSecondaryColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.priority),
                color = AppTheme.colorScheme.labelPrimaryColor,
                style = AppTheme.typographyScheme.title
            )
            PriorityItem(
                priority = Priority.LOW_PRIORITY,
                selectedPriority = selectedPriority,
                onPrioritySelect = {
                    currentOnPrioritySelect(it)
                    scope.launch { onDismissRequest() }
                }
            )
            PriorityItem(
                priority = Priority.USUAL_PRIORITY,
                selectedPriority = selectedPriority,
                onPrioritySelect = {
                    currentOnPrioritySelect(it)
                    scope.launch { onDismissRequest() }
                }
            )
            PriorityItem(
                priority = Priority.URGENT_PRIORITY,
                selectedPriority = selectedPriority,
                onPrioritySelect = {
                    currentOnPrioritySelect(it)
                    scope.launch { onDismissRequest() }
                }
            )
        }
    }
}

private const val SELECTED_ALPHA = 1.5f
private const val UNSELECTED_ALPHA = 1f
private const val FONT_WEIGHT = 500

@Composable
fun PriorityItem(
    priority: String,
    selectedPriority: String,
    onPrioritySelect: (String) -> Unit
) {
    val isSelected = selectedPriority == priority
    val priorityColor: androidx.compose.ui.graphics.Color = when (priority) {
        Priority.URGENT_PRIORITY -> AppTheme.colorScheme.redColor
        Priority.USUAL_PRIORITY -> AppTheme.colorScheme.labelPrimaryColor
        Priority.LOW_PRIORITY -> AppTheme.colorScheme.blueColor
        else -> AppTheme.colorScheme.labelPrimaryColor
    }

    var alpha by remember { mutableFloatStateOf(UNSELECTED_ALPHA) }
    val animatedAlpha by animateFloatAsState(
        targetValue = alpha,
        label = stringResource(R.string.animated_alpha)
    )

    LaunchedEffect(isSelected) {
        alpha = if (isSelected) SELECTED_ALPHA else UNSELECTED_ALPHA
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .alpha(animatedAlpha)
            .clickable { onPrioritySelect(priority) }
            .padding(16.dp)
    ) {
        Text(
            text = priority,
            fontSize = 16.sp,
            fontWeight = FontWeight(FONT_WEIGHT),
            color = priorityColor
        )
    }
}
