package com.example.project.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project.viewmodel.NoteViewModel
import com.example.project.viewmodel.SettingsViewModel
import com.example.project.screens.components.HorizontalCalendar
import com.example.project.screens.components.TimelineNoteItem
import com.example.project.theme.*
import com.example.project.platform.NetworkMonitor
import kotlinx.datetime.*
import kotlinx.coroutines.delay
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: NoteViewModel,
    settingsViewModel: SettingsViewModel,
    onNavigateToEdit: (Long) -> Unit,
    onNavigateToSettings: () -> Unit
) {
    val notes by viewModel.notes.collectAsState()
    val userName by settingsViewModel.userName.collectAsState()
    val isNewest by settingsViewModel.sortByNewest.collectAsState()

    val networkMonitor: NetworkMonitor = koinInject()
    val isConnected by networkMonitor.observeConnectivity().collectAsState(initial = true)

    var showOfflineBanner by remember { mutableStateOf(false) }

    LaunchedEffect(isConnected) {
        if (!isConnected) {
            showOfflineBanner = true
            delay(6000)
            showOfflineBanner = false
        } else {
            showOfflineBanner = false
        }
    }

    var searchQuery by remember { mutableStateOf("") }

    val displayNotes = notes
        .filter { it.title.contains(searchQuery, ignoreCase = true) }
        .let { filtered ->
            if (isNewest) filtered.sortedByDescending { it.id }
            else filtered.sortedBy { it.id }
        }

    val today = remember { Clock.System.todayIn(TimeZone.currentSystemDefault()) }
    val months = listOf("Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember")
    val days = listOf("Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu")
    val dateString = "${days[today.dayOfWeek.ordinal]}, ${today.dayOfMonth} ${months[today.monthNumber - 1]} ${today.year}"

    Column(modifier = Modifier.fillMaxSize().background(BgMain).padding(horizontal = 24.dp)) {
        Spacer(modifier = Modifier.height(40.dp))

        AnimatedVisibility(visible = showOfflineBanner) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(12.dp),
                color = Color(0xFFE74C3C)
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(Icons.Default.CloudOff, contentDescription = "Offline", tint = Color.White)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Mode Offline - Tidak ada internet", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Hallo, $userName", fontSize = 28.sp, fontWeight = FontWeight.ExtraBold, color = TextHeading)
                Text(text = dateString, fontSize = 14.sp, color = TextBody)
            }

            IconButton(
                onClick = onNavigateToSettings,
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(PrimaryLight)
            ) {
                Icon(Icons.Default.Settings, contentDescription = "Settings", tint = PrimaryTeal)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        HorizontalCalendar()
        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(containerColor = PrimaryTeal)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(text = "Cari Catatan", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(12.dp))
                TextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Ketik judul...", color = Color.Gray) },
                    leadingIcon = { Icon(Icons.Default.Search, null, tint = PrimaryTeal) },
                    modifier = Modifier.fillMaxWidth().height(54.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Catatan Harian", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = TextHeading)
        Spacer(modifier = Modifier.height(16.dp))

        if (displayNotes.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(top = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier.size(80.dp).clip(RoundedCornerShape(24.dp)).background(LineDotted),
                    contentAlignment = Alignment.Center
                ) { Text("📝", fontSize = 40.sp) }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = if (searchQuery.isNotEmpty()) "Catatan tidak ditemukan" else "Belum ada catatan",
                    fontWeight = FontWeight.Bold, color = TextHeading, fontSize = 16.sp
                )
                Text(
                    text = if (searchQuery.isNotEmpty()) "Coba kata kunci lain" else "Yuk, mulai tulis rutinitasmu!",
                    color = TextBody, fontSize = 14.sp
                )
            }
        } else {
            LazyColumn(contentPadding = PaddingValues(bottom = 100.dp)) {
                itemsIndexed(displayNotes) { index, note ->
                    TimelineNoteItem(
                        title = note.title,
                        content = note.content,
                        isFavorite = note.isFavorite == 1L,
                        isLast = index == displayNotes.lastIndex,
                        onClick = { onNavigateToEdit(note.id) },
                        onFavoriteToggle = { viewModel.toggleFavorite(note.id) }
                    )
                }
            }
        }
    }
}