package com.daniil.halushka.todoapp.presentation.screens.home

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.daniil.halushka.todoapp.R
import com.daniil.halushka.todoapp.presentation.screens.elements.home.ContainerWithTodo
import com.daniil.halushka.todoapp.presentation.screens.elements.home.CustomFAB
import com.daniil.halushka.todoapp.presentation.screens.elements.home.HomeTopBar
import com.daniil.halushka.todoapp.presentation.viewmodels.HomeScreenViewModel
import com.daniil.halushka.todoapp.util.events.EventManager
import com.daniil.halushka.todoapp.util.events.TodoEvent

@Composable
fun HomeScreen(
    navigationController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val listState = rememberLazyListState()
    val toolbarHeight by rememberToolbarHeight(listState)

    val showFinished by viewModel.showFinishedTodo.collectAsStateWithLifecycle()
    val completedItemsCount by viewModel.quantityOfFinishedTodo.collectAsStateWithLifecycle()

    val nestedScrollConnection = createNestedScrollConnection(listState)

    LaunchedEffect(Unit) {
        EventManager.events.collect { event ->
            when (event) {
                is TodoEvent.TodoListUpdated -> viewModel.getTodoList()
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection)
    ) {
        Column {
            HomeTopBar(
                completedItemsCount = completedItemsCount,
                onEyeIconClick = { showTask -> viewModel.showFinishedTodo(showTask) },
                showFinished = showFinished,
                height = toolbarHeight
            )
            ContainerWithTodo(
                navigationController = navigationController,
                viewModel = viewModel,
                listState = listState
            )
        }
        BottomEndFAB(navigationController)
    }
}


@Composable
fun rememberToolbarHeight(listState: LazyListState): State<Dp> {
    val toolbarHeight = remember {
        derivedStateOf {
            if (listState.firstVisibleItemScrollOffset > 0) 64.dp else 100.dp
        }
    }

    return animateDpAsState(
        targetValue = toolbarHeight.value,
        label = stringResource(id = R.string.toolbar)
    )
}


fun createNestedScrollConnection(listState: LazyListState): NestedScrollConnection {
    return object : NestedScrollConnection {
        override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
            return if (available.y > 0
                && listState.firstVisibleItemIndex == 0
                && listState.firstVisibleItemScrollOffset == 0
            ) {
                Offset.Zero
            } else {
                Offset(available.x, 0f)
            }
        }

        override fun onPostScroll(
            consumed: Offset,
            available: Offset,
            source: NestedScrollSource
        ): Offset {
            return if (available.y < 0
                && listState.firstVisibleItemIndex == 0
                && listState.firstVisibleItemScrollOffset == 0
            ) {
                Offset.Zero
            } else {
                Offset(available.x, 0f)
            }
        }
    }
}

@Composable
fun BottomEndFAB(navigationController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues()),
        contentAlignment = Alignment.BottomEnd
    ) {
        CustomFAB(navigationController = navigationController)
    }
}