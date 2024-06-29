package com.daniil.halushka.todoapp.presentation.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daniil.halushka.todoapp.data.models.TodoItem
import com.daniil.halushka.todoapp.domain.usecases.details.DeleteTodo
import com.daniil.halushka.todoapp.domain.usecases.details.GetUniqueTodo
import com.daniil.halushka.todoapp.domain.usecases.details.SaveTodo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val getUniqueTodo: GetUniqueTodo,
    private val saveTodo: SaveTodo,
    private val deleteTodo: DeleteTodo
) : ViewModel() {
    private val _uniqueTodo = MutableStateFlow<TodoItem?>(null)
    val uniqueTodo: StateFlow<TodoItem?> get() = _uniqueTodo

    fun getUniqueTodo(id: String) {
        viewModelScope.launch {
            val todoItem = getUniqueTodo.getUniqueTodo(id)
            _uniqueTodo.value = todoItem
        }
    }

    fun saveTodo(todoItem: TodoItem) {
        viewModelScope.launch {
            saveTodo.saveTodo(todoItem)
        }
    }

    fun deleteTodo(id: String){
        viewModelScope.launch {
            deleteTodo.deleteTodo(id)
        }
    }
}