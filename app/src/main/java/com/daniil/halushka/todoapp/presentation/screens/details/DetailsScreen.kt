package com.daniil.halushka.todoapp.presentation.screens.details

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.daniil.halushka.todoapp.data.models.TodoItem
import com.daniil.halushka.todoapp.presentation.navigation.ScreenRoutes
import com.daniil.halushka.todoapp.presentation.screens.elements.details.DetailsCollapsedDropdown
import com.daniil.halushka.todoapp.presentation.screens.elements.details.DetailsDeadlineBlock
import com.daniil.halushka.todoapp.presentation.screens.elements.details.DetailsDeleteButton
import com.daniil.halushka.todoapp.presentation.screens.elements.details.DetailsSeparator
import com.daniil.halushka.todoapp.presentation.screens.elements.details.DetailsTextField
import com.daniil.halushka.todoapp.presentation.screens.elements.details.DetailsTopBar
import com.daniil.halushka.todoapp.presentation.screens.elements.details.PriorityBottomSheet
import com.daniil.halushka.todoapp.presentation.viewmodels.DetailsScreenViewModel
import com.daniil.halushka.todoapp.ui.theme.AppTheme
import com.daniil.halushka.todoapp.ui.theme.TodoAppTheme
import com.daniil.halushka.todoapp.util.constants.NullableTodo
import kotlinx.coroutines.launch

/**
 * Composable function representing the Details screen of the application.
 * @param navigationController NavController used for navigation.
 * @param viewModel ViewModel for managing Details screen state.
 * @param todoId Optional todoItem id to load details.
 */

@Composable
fun DetailsScreen(
    navigationController: NavController,
    viewModel: DetailsScreenViewModel = hiltViewModel(),
    todoId: String? = null
) {
    if (navigationController.previousBackStackEntry?.destination?.route == ScreenRoutes.HomeScreen.screenType) {
        todoId?.let {
            viewModel.getUniqueTodo(it)
        }
    }

    val uniqueTodo by viewModel.uniqueTodo.collectAsStateWithLifecycle()
    val todoItem: TodoItem =
        if (todoId == null) NullableTodo.nullableModel else uniqueTodo ?: NullableTodo.nullableModel

    var todoText by remember { mutableStateOf(todoItem.text) }
    var showBottomSheet by remember { mutableStateOf(false) }
    var selectedPriority by remember { mutableStateOf(todoItem.priority) }
    var selectedDate by remember { mutableStateOf(todoItem.deadline) }

    LaunchedEffect(todoItem) {
        todoText = todoItem.text
        selectedPriority = todoItem.priority
        selectedDate = todoItem.deadline
    }

    val scope = rememberCoroutineScope()

    Box {
        if (showBottomSheet) {
            PriorityBottomSheet(
                showBottomSheet = true,
                onDismissRequest = { showBottomSheet = false },
                selectedPriority = selectedPriority,
                onPrioritySelect = {
                    selectedPriority = it
                    scope.launch { showBottomSheet = false }
                }
            )
        }

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
                },
                onSaveClick = {
                    viewModel.saveOrUpdateTodo(
                        todoItem.copy(
                            text = todoText,
                            priority = selectedPriority,
                            deadline = selectedDate
                        )
                    )
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
                        isClicked = {
                            scope.launch { showBottomSheet = true }
                        }
                    )
                }

                DetailsSeparator()

                DetailsDeadlineBlock(
                    getDeadlineDate = { selectedDate },
                    onDateSelect = { date ->
                        selectedDate = date
                    }
                )

                DetailsSeparator()

                DetailsDeleteButton(
                    todoId = todoId ?: "",
                    onDeleteClick = { id ->
                        viewModel.deleteTodo(id)
                        navigationController.popBackStack()
                    }
                )
            }
        }
    }
}

@Composable
@Preview(name = "Light version")
fun DetailsScreenPreview() {
    TodoAppTheme {
        val navigationController = rememberNavController()
        DetailsScreen(navigationController = navigationController)
    }
}

@Composable
@Preview(name = "Dark version", uiMode = Configuration.UI_MODE_NIGHT_YES)
fun DetailsScreenPreviewDark() {
    TodoAppTheme {
        val navigationController = rememberNavController()
        DetailsScreen(navigationController = navigationController)
    }
}
