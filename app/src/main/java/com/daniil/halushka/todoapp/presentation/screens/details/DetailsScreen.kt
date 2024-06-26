package com.daniil.halushka.todoapp.presentation.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.daniil.halushka.todoapp.constants.Priority
import com.daniil.halushka.todoapp.data.models.TodoItem
import com.daniil.halushka.todoapp.presentation.navigation.ScreenRoutes
import com.daniil.halushka.todoapp.presentation.screens.elements.details.DetailsCollapsedDropdown
import com.daniil.halushka.todoapp.presentation.screens.elements.details.DetailsDeadlineBlock
import com.daniil.halushka.todoapp.presentation.screens.elements.details.DetailsDeleteButton
import com.daniil.halushka.todoapp.presentation.screens.elements.details.DetailsExpandedDropdown
import com.daniil.halushka.todoapp.presentation.screens.elements.details.DetailsSeparator
import com.daniil.halushka.todoapp.presentation.screens.elements.details.DetailsTextField
import com.daniil.halushka.todoapp.presentation.screens.elements.details.DetailsTopBar
import com.daniil.halushka.todoapp.ui.theme.AppTheme

@Composable
fun DetailsScreen(
    navigationController: NavController,
    todoItem: TodoItem? = null
) {
    //TODO add viewmodel in future
    when (navigationController.previousBackStackEntry?.destination?.route) {
        ScreenRoutes.HomeScreen.screenType -> {}
        ScreenRoutes.DetailsScreen.screenType -> {}
    }

    var dropdownClick: Boolean by remember { mutableStateOf(false) }
    var todoText by remember { mutableStateOf(todoItem?.text ?: "") }
    var selectedPriority by remember { mutableStateOf(todoItem?.priority ?: Priority.USUAL_PRIORITY) }
    var selectedDate by remember { mutableStateOf(todoItem?.deadline) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colorScheme.backPrimaryColor)
            .padding(end = 4.dp),
        horizontalAlignment = Alignment.Start
    ) {
        DetailsTopBar(
            clickOnNavigationItem = {
                navigationController.popBackStack()
            }
        )

        Column {
            DetailsTextField(
                text = todoText,
                onTextChange = { newText -> todoText = newText }
            )

            Row {
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

            DetailsSeparator()

            DetailsDeadlineBlock(
                getDeadlineDate = { selectedDate },
                onDateSelect = { date -> selectedDate = date }
            )

            DetailsSeparator()

            DetailsDeleteButton(
                clickOnNavigationItem = {
                    navigationController.popBackStack()
                }
            )
        }
    }
}
