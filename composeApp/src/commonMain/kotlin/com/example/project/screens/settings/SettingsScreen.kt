package com.example.project.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project.theme.*
import com.example.project.viewmodel.SettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(viewModel: SettingsViewModel, onBack: () -> Unit) {
    val name by viewModel.userName.collectAsState()
    val isNewest by viewModel.sortByNewest.collectAsState()
    var tempName by remember { mutableStateOf(name) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pengaturan", color = TextHeading) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null, tint = PrimaryTeal)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = BgMain)
            )
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().background(BgMain).padding(padding).padding(24.dp)) {
            Text(text = "Profil", fontSize = 18.sp, color = TextHeading)
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = tempName,
                onValueChange = { tempName = it },
                label = { Text("Ubah Nama Panggilan") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(text = "Urutan Catatan", fontSize = 18.sp, color = TextHeading)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Urutkan dari yang terbaru", modifier = Modifier.weight(1f), color = TextBody)
                Switch(
                    checked = isNewest,
                    onCheckedChange = { viewModel.toggleSortOrder(it) },
                    colors = SwitchDefaults.colors(checkedThumbColor = PrimaryTeal)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { viewModel.updateName(tempName); onBack() },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryTeal)
            ) {
                Text("Simpan Perubahan")
            }
        }
    }
}