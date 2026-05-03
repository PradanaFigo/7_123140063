package com.example.project.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.data.ai.AiRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AiUiState(
    val resultText: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

class AiViewModel(private val repository: AiRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(AiUiState())
    val uiState: StateFlow<AiUiState> = _uiState.asStateFlow()

    fun summarizeNote(content: String) {
        if (content.isBlank()) return
        _uiState.update { it.copy(isLoading = true, error = null) }
        viewModelScope.launch {
            repository.summarize(content).onSuccess { result ->
                _uiState.update { it.copy(resultText = result, isLoading = false) }
            }.onFailure { error ->
                _uiState.update { it.copy(error = error.message, isLoading = false) }
            }
        }
    }

    fun translateNote(content: String, targetLang: String = "Inggris") {
        if (content.isBlank()) return
        _uiState.update { it.copy(isLoading = true, error = null) }
        viewModelScope.launch {
            repository.translate(content, targetLang).onSuccess { result ->
                _uiState.update { it.copy(resultText = result, isLoading = false) }
            }.onFailure { error ->
                _uiState.update { it.copy(error = error.message, isLoading = false) }
            }
        }
    }

    fun clearResult() {
        _uiState.update { it.copy(resultText = "", error = null) }
    }
}