package com.example.project.screens.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project.viewmodel.NoteViewModel
import com.example.project.viewmodel.AiViewModel
import com.example.project.theme.*
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditScreen(
    noteId: Long? = null,
    viewModel: NoteViewModel,
    onBack: () -> Unit
) {
    val aiViewModel: AiViewModel = koinInject()
    val aiState by aiViewModel.uiState.collectAsState()

    val existingNote = remember(noteId) {
        noteId?.let { viewModel.getNoteById(it) }
    }

    var title by remember { mutableStateOf(existingNote?.title ?: "") }
    var content by remember { mutableStateOf(existingNote?.content ?: "") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        if (noteId == null) "Tambah Catatan" else "Edit Catatan",
                        fontWeight = FontWeight.Bold,
                        color = TextHeading
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali", tint = PrimaryTeal)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = BgMain)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BgMain)
                .padding(padding)
                .padding(24.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Judul Catatan") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryTeal,
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = content,
                onValueChange = { content = it },
                label = { Text("Tulis sesuatu...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryTeal,
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick = { aiViewModel.summarizeNote(content) },
                    enabled = !aiState.isLoading && content.isNotBlank(),
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Rangkum AI", color = PrimaryTeal)
                }

                OutlinedButton(
                    onClick = { aiViewModel.translateNote(content, "Inggris") },
                    enabled = !aiState.isLoading && content.isNotBlank(),
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Translate AI", color = PrimaryTeal)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (aiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = PrimaryTeal
                )
                Spacer(modifier = Modifier.height(16.dp))
            } else if (aiState.resultText.isNotBlank()) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = PrimaryLight),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Hasil AI:", fontWeight = FontWeight.Bold, color = TextHeading)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(aiState.resultText, color = TextBody, fontSize = 14.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        TextButton(
                            onClick = {
                                content = aiState.resultText
                                aiViewModel.clearResult()
                            },
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Text("Ganti isi catatan", color = PrimaryTeal)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            } else if (aiState.error != null) {
                Text(
                    text = "Error: ${aiState.error}",
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            Button(
                onClick = {
                    if (title.isNotEmpty() && content.isNotEmpty()) {
                        if (noteId == null) {
                            viewModel.addNote(title, content)
                        } else {
                            viewModel.updateNote(noteId, title, content, existingNote?.isFavorite == 1L)
                        }
                        onBack()
                    }
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryTeal)
            ) {
                Text("Simpan Catatan", fontSize = 16.sp, color = Color.White)
            }

            if (noteId != null) {
                TextButton(
                    onClick = {
                        viewModel.deleteNote(noteId)
                        onBack()
                    },
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                ) {
                    Text("Hapus Catatan", color = Color.Red)
                }
            }
        }
    }
}