package com.example.playwithcompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.playwithcompose.models.Components
import com.example.playwithcompose.repository.ComponentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {
    private val repository = ComponentRepository()

    private val _components = MutableStateFlow<List<Components>>(emptyList())
    val components: StateFlow<List<Components>> get() = _components

    init {
        fetchComponents()
    }

    private fun fetchComponents() {
        viewModelScope.launch {
            val fetchedComponents = repository.getComponents()
            _components.value = fetchedComponents
        }
    }
}