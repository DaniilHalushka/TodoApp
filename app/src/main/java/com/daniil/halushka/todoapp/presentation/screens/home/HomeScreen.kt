package com.daniil.halushka.todoapp.presentation.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.daniil.halushka.todoapp.constants.Priority
import com.daniil.halushka.todoapp.data.models.TodoItem
import com.daniil.halushka.todoapp.data.repository.TodoViewModel
import com.daniil.halushka.todoapp.presentation.screens.elements.home.ContainerWithTodo
import com.daniil.halushka.todoapp.presentation.screens.elements.home.CustomFAB
import java.util.UUID

@Composable
fun HomeScreen(
    navigationController: NavController,
    viewModel: TodoViewModel,
    onEditItem: (TodoItem) -> Unit,
) {
    val todoList by viewModel.todoList.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        ContainerWithTodo(
            todoList = todoList,
            onEditItem = onEditItem
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            CustomFAB(
                onClick = {
                    viewModel.addTodoItem(TodoItem(
                        id = UUID.randomUUID().toString(),
                        text = "",
                        priority = Priority.USUAL_PRIORITY,
                        startDate = System.currentTimeMillis(),
                        isDone = false
                    ))
                },
            )
        }
    }
}