package com.daniil.halushka.todoapp.presentation.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daniil.halushka.todoapp.data.models.TodoItem
import com.daniil.halushka.todoapp.domain.usecases.details.DeleteTodo
import com.daniil.halushka.todoapp.domain.usecases.details.GetUniqueTodo
import com.daniil.halushka.todoapp.domain.usecases.details.SaveTodo
import com.daniil.halushka.todoapp.domain.usecases.details.UpdateTodoDeadline
import com.daniil.halushka.todoapp.domain.usecases.details.UpdateTodoPriority
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val getUniqueTodo: GetUniqueTodo,
    private val saveTodo: SaveTodo,
    private val updateTodoPriority: UpdateTodoPriority,
    private val updateTodoDeadline: UpdateTodoDeadline,
    private val deleteTodo: DeleteTodo
) : ViewModel() {
    private val _uniqueTodo = MutableStateFlow<TodoItem?>(null)
    val uniqueTodo: StateFlow<TodoItem?> get() = _uniqueTodo

    fun getUniqueTodo(id: String) {
        viewModelScope.launch {
            val todoItem = getUniqueTodo.getUniqueTodoFromList(id)
            _uniqueTodo.value = todoItem
        }
    }

    fun saveTodo(todoItem: TodoItem) {
        viewModelScope.launch {
            saveTodo.saveTodoInList(todoItem)
        }
    }

    fun updateTodoPriority(todoId: String, newPriority: String) {
        viewModelScope.launch {
            updateTodoPriority.updatePriorityInTodo(todoId, newPriority)
        }
    }

    fun updateTodoDeadline(todoId: String, newDeadline: String) {
        viewModelScope.launch {
            updateTodoDeadline.updateDeadlineInTodo(todoId, newDeadline)
        }
    }

    fun deleteTodo(id: String){
        viewModelScope.launch {
            deleteTodo.deleteTodoInList(id)
        }
    }
}