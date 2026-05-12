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

data class ChatMessage(val text: String, val isUser: Boolean)

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

    fun translateNote(content: String, targetLang: String = "Indonesia") {
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

    private val _chatMessages = MutableStateFlow<List<ChatMessage>>(
        listOf(ChatMessage("Halo! Aku AI Asisten. Ada yang ingin kamu tanyakan atau rangkum hari ini?", isUser = false))
    )
    val chatMessages: StateFlow<List<ChatMessage>> = _chatMessages.asStateFlow()

    private val _isChatLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isChatLoading.asStateFlow()

    fun sendMessageToAi(prompt: String) {
        val currentList = _chatMessages.value.toMutableList()
        currentList.add(ChatMessage(text = prompt, isUser = true))
        _chatMessages.value = currentList

        _isChatLoading.value = true

        viewModelScope.launch {
            try {
                repository.generateContent(prompt).onSuccess { result ->
                    val updatedList = _chatMessages.value.toMutableList()
                    updatedList.add(ChatMessage(text = result, isUser = false))
                    _chatMessages.value = updatedList
                }.onFailure { error ->
                    val updatedList = _chatMessages.value.toMutableList()
                    updatedList.add(ChatMessage(text = "Maaf, error: ${error.message}", isUser = false))
                    _chatMessages.value = updatedList
                }
            } catch (e: Exception) {
                val updatedList = _chatMessages.value.toMutableList()
                updatedList.add(ChatMessage(text = "Error: ${e.message}", isUser = false))
                _chatMessages.value = updatedList
            } finally {
                _isChatLoading.value = false
            }
        }
    }
}