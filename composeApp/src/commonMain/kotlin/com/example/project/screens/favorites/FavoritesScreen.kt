package com.example.project.screens.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project.viewmodel.NoteViewModel
import com.example.project.screens.components.TimelineNoteItem
import com.example.project.theme.*

@Composable
fun FavoritesScreen(viewModel: NoteViewModel, onNavigateToEdit: (Long) -> Unit) {
    val notes by viewModel.notes.collectAsState()
    val favorites = notes.filter { it.isFavorite == 1L }

    Column(modifier = Modifier.fillMaxSize().background(BgMain).padding(horizontal = 24.dp)) {
        Spacer(modifier = Modifier.height(48.dp))
        Text(text = "Favorit Saya", fontSize = 28.sp, fontWeight = FontWeight.ExtraBold, color = TextHeading)
        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(contentPadding = PaddingValues(bottom = 100.dp)) {
            itemsIndexed(favorites) { index, note ->
                TimelineNoteItem(
                    title = note.title,
                    content = note.content,
                    isFavorite = true,
                    isLast = index == favorites.lastIndex,
                    onClick = { onNavigateToEdit(note.id) },
                    onFavoriteToggle = { viewModel.toggleFavorite(note.id) }
                )
            }
        }
    }
}