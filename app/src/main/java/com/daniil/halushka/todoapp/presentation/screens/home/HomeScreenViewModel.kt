package com.daniil.halushka.todoapp.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daniil.halushka.todoapp.data.models.TodoItem
import com.daniil.halushka.todoapp.domain.usecases.home.CountFinishedTodo
import com.daniil.halushka.todoapp.domain.usecases.home.FinishTodo
import com.daniil.halushka.todoapp.domain.usecases.home.ReceiveTodoList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val receiveTodoList: ReceiveTodoList,
    private val countFinishedTasks: CountFinishedTodo,
    private val finishTodo: FinishTodo
) : ViewModel() {
    private val _todoList = MutableStateFlow<List<TodoItem>>(emptyList())
    val todoList: StateFlow<List<TodoItem>> get() = _todoList

    private val _quantityOfFinishedTodo = MutableStateFlow(0)
    val quantityOfFinishedTodo: StateFlow<Int> get() = _quantityOfFinishedTodo

    private val _showFinishedTodo = MutableStateFlow(false)
    val showFinishedTodo: StateFlow<Boolean> get() = _showFinishedTodo

    init {
        getTodoList()
    }

    private fun getTodoList() {
        viewModelScope.launch {
            _todoList.value = receiveTodoList.receiveTodoList()
            _quantityOfFinishedTodo.value = countFinishedTasks.countTasks()
        }
    }

    fun showFinishedTodo(isShowed: Boolean) {
        _showFinishedTodo.value = isShowed
    }

    fun finishTodo(todoId: String, isTodoDone: Boolean) {
        viewModelScope.launch {
            finishTodo.finishTodo(todoId, isTodoDone)
            getTodoList()
        }
    }
}
