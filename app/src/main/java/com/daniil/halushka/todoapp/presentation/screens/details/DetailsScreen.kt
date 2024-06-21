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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daniil.halushka.todoapp.constants.Priority
import com.daniil.halushka.todoapp.data.models.TodoItem
import com.daniil.halushka.todoapp.data.repository.TodoRepository
import com.daniil.halushka.todoapp.data.repository.TodoViewModel
import com.daniil.halushka.todoapp.presentation.events.ItemModificationEvent
import com.daniil.halushka.todoapp.presentation.screens.elements.details.DetailsCollapsedDropdown
import com.daniil.halushka.todoapp.presentation.screens.elements.details.DetailsDeadlineBlock
import com.daniil.halushka.todoapp.presentation.screens.elements.details.DetailsDeleteButton
import com.daniil.halushka.todoapp.presentation.screens.elements.details.DetailsExpandedDropdown
import com.daniil.halushka.todoapp.presentation.screens.elements.details.DetailsSeparator
import com.daniil.halushka.todoapp.presentation.screens.elements.details.DetailsTextField
import com.daniil.halushka.todoapp.presentation.screens.elements.details.DetailsTopBar
import com.daniil.halushka.todoapp.ui.theme.TodoAppTheme
import java.util.UUID

@Composable
fun DetailsScreen(
    viewModel: TodoViewModel,
    todoItem: TodoItem?,
    getDeadlineDate: () -> Long?,
    isDeleteClicked: () -> Boolean,
) {
    var dropdownClick: Boolean by remember { mutableStateOf(false) }
    var todoText by remember { mutableStateOf(todoItem?.text ?: "") }
    var selectedPriority by remember {
        mutableStateOf(
            todoItem?.priority ?: Priority.USUAL_PRIORITY
        )
    }
    var selectedDate by remember { mutableStateOf(getDeadlineDate()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding(end = 4.dp),
        horizontalAlignment = Alignment.Start
    ) {
        DetailsTopBar(
            receiveEvent = { event ->
                {
                    when (event) {
                        is ItemModificationEvent.Save -> {
                            viewModel.updateTodoItem(
                                TodoItem(
                                    id = todoItem?.id ?: UUID.randomUUID().toString(),
                                    text = todoText,
                                    priority = selectedPriority,
                                    startDate = todoItem?.startDate ?: System.currentTimeMillis(),
                                    isDone = false,
                                    deadline = selectedDate
                                )
                            )
                        }

                        is ItemModificationEvent.Delete -> {
                            todoItem?.id?.let { viewModel.deleteTodoItem(it) }
                        }

                        is ItemModificationEvent.Exit -> {
                            // handle exit
                        }

                        else -> {}
                    }
                }
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
                isClicked = { isDeleteClicked() },
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

@Preview(showBackground = true)
@Composable
fun PreviewDetailsScreen(){
    TodoAppTheme {
        DetailsScreen(
            viewModel = TodoViewModel(TodoRepository()),
            todoItem = TodoRepository().getTodoList().component3(),
            getDeadlineDate = { 1L },
            isDeleteClicked = { true }
        )
    }
}