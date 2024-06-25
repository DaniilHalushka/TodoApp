package com.daniil.halushka.todoapp.presentation.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.daniil.halushka.todoapp.presentation.screens.elements.home.ContainerWithTodo
import com.daniil.halushka.todoapp.presentation.screens.elements.home.CustomFAB

@Composable
fun HomeScreen(
    navigationController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ContainerWithTodo(
            navigationController = navigationController,
            viewModel = viewModel
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            CustomFAB(
                navigationController = navigationController
            )
        }
    }
}
