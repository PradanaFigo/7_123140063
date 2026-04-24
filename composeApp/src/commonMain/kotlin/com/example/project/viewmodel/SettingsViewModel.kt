package com.example.project.viewmodel

import androidx.lifecycle.ViewModel
import com.example.project.data.SettingsManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SettingsViewModel(private val settingsManager: SettingsManager) : ViewModel() {
    private val _userName = MutableStateFlow(settingsManager.userName)
    val userName = _userName.asStateFlow()

    private val _sortByNewest = MutableStateFlow(settingsManager.sortByNewest)
    val sortByNewest = _sortByNewest.asStateFlow()

    fun updateName(newName: String) {
        settingsManager.userName = newName
        _userName.value = newName
    }

    fun toggleSortOrder(isNewest: Boolean) {
        settingsManager.sortByNewest = isNewest
        _sortByNewest.value = isNewest
    }
}