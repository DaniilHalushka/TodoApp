package com.daniil.halushka.todoapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daniil.halushka.todoapp.data.models.TodoItem
import com.daniil.halushka.todoapp.domain.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: TodoRepository
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
            _todoList.value = repository.getTodoList()
            _quantityOfFinishedTodo.value = repository.countFinishedTodo()
        }
    }

    fun showFinishedTodo(isShowed: Boolean) {
        _showFinishedTodo.value = isShowed
    }

    fun finishTodo(todoId: String, isTodoDone: Boolean) {
        viewModelScope.launch {
            repository.finishTodo(todoId, isTodoDone)
            getTodoList()
        }
    }
}
