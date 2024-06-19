package com.daniil.halushka.todoapp.presentation.screens.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daniil.halushka.todoapp.R
import com.daniil.halushka.todoapp.data.repository.TodoRepository
import com.daniil.halushka.todoapp.ui.theme.TodoAppTheme

@Composable
fun CustomTopAppBar(
    completedItemsCount: Int,
    scrollState: androidx.compose.foundation.ScrollState
) {
    val maxHeight = 200.dp
    val minHeight = 56.dp

    BoxWithConstraints(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
    ) {
        val constraintsMaxHeight = maxHeight.coerceAtMost(this@BoxWithConstraints.maxHeight)
        val constraintsMinHeight = minHeight.coerceAtMost(this@BoxWithConstraints.minHeight)

        val offset by remember {
            derivedStateOf {
                (scrollState.value.toFloat() / scrollState.maxValue) * (constraintsMaxHeight - constraintsMinHeight).value
            }
        }

        val appBarHeight = constraintsMaxHeight - offset.dp

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(appBarHeight)
                .background(MaterialTheme.colorScheme.primary)
        ) {
            Row {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = stringResource(R.string.my_tasks_todo_s),
                        fontSize = if (appBarHeight > minHeight) 24.sp else 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    if (appBarHeight > minHeight) {
                        Text(
                            text = stringResource(
                                id = R.string.completed_todo_s,
                                completedItemsCount.toString()
                            ),
                            fontSize = 20.sp,
                            color = Color.Gray
                        )
                    }
                }
                IconButton(
                    onClick = { /* TODO handle eye click */ }
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = stringResource(id = R.string.view_completed_todo_s)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview1() {
    TodoAppTheme {
        val scrollState = rememberScrollState()
        Column {
            CustomTopAppBar(completedItemsCount = 5, scrollState = scrollState)
            ContainerWithTodo(repository = TodoRepository()) {}
        }

    }
}
