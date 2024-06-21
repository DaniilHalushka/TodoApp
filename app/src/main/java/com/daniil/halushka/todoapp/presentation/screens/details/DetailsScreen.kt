package com.daniil.halushka.todoapp.presentation.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
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
import com.daniil.halushka.todoapp.data.repository.TodoViewModel
import com.daniil.halushka.todoapp.presentation.events.ItemModificationEvent
import com.daniil.halushka.todoapp.presentation.navigation.ScreenRoutes
import com.daniil.halushka.todoapp.presentation.screens.elements.details.DetailsCollapsedDropdown
import com.daniil.halushka.todoapp.presentation.screens.elements.details.DetailsDeadlineBlock
import com.daniil.halushka.todoapp.presentation.screens.elements.details.DetailsDeleteButton
import com.daniil.halushka.todoapp.presentation.screens.elements.details.DetailsExpandedDropdown
import com.daniil.halushka.todoapp.presentation.screens.elements.details.DetailsSeparator
import com.daniil.halushka.todoapp.presentation.screens.elements.details.DetailsTextField
import com.daniil.halushka.todoapp.presentation.screens.elements.details.DetailsTopBar

@Composable
fun DetailsScreen(
    navigationController: NavController,
    viewModel: TodoViewModel,
    todoItem: TodoItem? = null,
    getDeadlineDate: () -> Long? = { null },
) {

    when (navigationController.previousBackStackEntry?.destination?.route) {
        ScreenRoutes.HomeScreen.screenType -> {}

        ScreenRoutes.DetailsScreen.screenType -> {}
    }

    var dropdownClick: Boolean by remember { mutableStateOf(false) }

    var todoText by remember { mutableStateOf(todoItem?.text ?: "") }
    var selectedPriority by remember {
        mutableStateOf(
            todoItem?.priority ?: Priority.USUAL_PRIORITY
        )
    }

    var selectedDate by remember { mutableStateOf(getDeadlineDate()) }

    var isDeleteClicked: Boolean by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
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
                onTextChange = { newText -> todoText = newText },
                receiveEvent = { event ->
                    {
                        when (event) {
                            is ItemModificationEvent.UpdateName -> {
                                todoText = event.newName
                            }

                            else -> {}
                        }
                    }
                }
            )

            Row {
                DetailsCollapsedDropdown(
                    priority = selectedPriority,
                    isClicked = { click -> dropdownClick = click }
                )
                DetailsExpandedDropdown(
                    expanded = dropdownClick,
                    clickToExpand = { dropdownClick = it },
                    receiveEvent = { event ->
                        {
                            when (event) {
                                is ItemModificationEvent.UpdatePriority -> {
                                    selectedPriority = event.newPriority
                                }

                                else -> {}
                            }
                        }
                    }
                )
            }

            DetailsSeparator()

            DetailsDeadlineBlock(
                getDeadlineDate = { selectedDate },
                onDateSelect = { date -> selectedDate = date }
            )

            DetailsSeparator()

            DetailsDeleteButton(
                isClicked = isDeleteClicked,
                onDeleteClick = { isDeleteClicked = !isDeleteClicked },
                receiveEvent = { event ->
                    {
                        when (event) {
                            is ItemModificationEvent.Delete -> {
                                todoItem?.id?.let { viewModel.deleteTodoItem(it) }
                            }

                            else -> {}
                        }
                    }
                }
            )
        }
    }
}