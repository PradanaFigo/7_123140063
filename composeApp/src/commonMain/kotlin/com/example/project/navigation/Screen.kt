package com.example.project.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorites : Screen("favorites")
    object AddEdit : Screen("add_edit/{noteId}") {
        fun createRoute(noteId: Long) = "add_edit/$noteId"
    }
}