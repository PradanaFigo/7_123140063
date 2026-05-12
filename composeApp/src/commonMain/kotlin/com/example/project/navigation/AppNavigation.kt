package com.example.project.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.project.screens.home.HomeScreen
import com.example.project.screens.favorites.FavoritesScreen
import com.example.project.screens.notes.AddEditScreen
import com.example.project.screens.settings.SettingsScreen
import com.example.project.screens.ai.AiChatScreen
import com.example.project.theme.*
import com.example.project.viewmodel.NoteViewModel
import com.example.project.viewmodel.SettingsViewModel
import com.example.project.viewmodel.AiViewModel

@Composable
fun AppNavigation(
    viewModel: NoteViewModel,
    settingsViewModel: SettingsViewModel,
    aiViewModel: AiViewModel
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute == Screen.Home.route || currentRoute == Screen.Favorites.route || currentRoute == "chat_ai_screen") {
                NavigationBar(containerColor = CardWhite, tonalElevation = 8.dp) {

                    // 1. Tombol Home
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Home, null) },
                        label = { Text("Home") },
                        selected = currentRoute == Screen.Home.route,
                        onClick = {
                            if (currentRoute != Screen.Home.route) {
                                navController.navigate(Screen.Home.route) { popUpTo(Screen.Home.route) { inclusive = true } }
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = PrimaryTeal,
                            selectedTextColor = PrimaryTeal,
                            indicatorColor = PrimaryLight
                        )
                    )

                    // 2. Tombol Favorit
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Favorite, null) },
                        label = { Text("Favorit") },
                        selected = currentRoute == Screen.Favorites.route,
                        onClick = {
                            if (currentRoute != Screen.Favorites.route) {
                                navController.navigate(Screen.Favorites.route)
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = PrimaryTeal,
                            selectedTextColor = PrimaryTeal,
                            indicatorColor = PrimaryLight
                        )
                    )

                    // 3. Tombol Tanya AI
                    NavigationBarItem(
                        icon = { Icon(Icons.Default.Star, null) },
                        label = { Text("Tanya AI") },
                        selected = currentRoute == "chat_ai_screen",
                        onClick = {
                            if (currentRoute != "chat_ai_screen") {
                                navController.navigate("chat_ai_screen")
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = PrimaryTeal,
                            selectedTextColor = PrimaryTeal,
                            indicatorColor = PrimaryLight
                        )
                    )
                }
            }
        },
        floatingActionButton = {
            if (currentRoute == Screen.Home.route || currentRoute == Screen.Favorites.route) {
                FloatingActionButton(
                    onClick = { navController.navigate(Screen.AddEdit.createRoute(-1L)) },
                    containerColor = PrimaryTeal,
                    shape = RoundedCornerShape(18.dp)
                ) { Icon(Icons.Default.Add, null, tint = Color.White) }
            }
        }
    ) { padding ->
        NavHost(navController = navController, startDestination = Screen.Home.route, modifier = Modifier.padding(padding)) {
            composable(Screen.Home.route) {
                HomeScreen(
                    viewModel = viewModel,
                    settingsViewModel = settingsViewModel,
                    onNavigateToEdit = { id: Long -> navController.navigate(Screen.AddEdit.createRoute(id)) },
                    onNavigateToSettings = { navController.navigate("settings") }
                )
            }
            composable(Screen.Favorites.route) {
                FavoritesScreen(
                    viewModel = viewModel,
                    onNavigateToEdit = { id: Long -> navController.navigate(Screen.AddEdit.createRoute(id)) }
                )
            }
            composable("settings") {
                SettingsScreen(
                    viewModel = settingsViewModel,
                    onBack = { navController.popBackStack() }
                )
            }
            composable(
                route = Screen.AddEdit.route,
                arguments = listOf(navArgument("noteId") { type = NavType.LongType })
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getLong("noteId") ?: -1L
                AddEditScreen(
                    noteId = if (id == -1L) null else id,
                    viewModel = viewModel,
                    onBack = { navController.popBackStack() }
                )
            }

            composable("chat_ai_screen") {
                AiChatScreen(
                    viewModel = aiViewModel,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}