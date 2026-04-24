package com.example.project.data

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import com.russhwolf.settings.get

class SettingsManager(private val settings: Settings) {
    companion object {
        private const val KEY_USER_NAME = "user_name"
        private const val KEY_SORT_NEWEST = "sort_by_newest"
    }

    var userName: String
        get() = settings[KEY_USER_NAME, "Pradana"]
        set(value) { settings[KEY_USER_NAME] = value }

    var sortByNewest: Boolean
        get() = settings[KEY_SORT_NEWEST, true]
        set(value) { settings[KEY_SORT_NEWEST] = value }
}