package com.example.project.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project.theme.*
import com.example.project.viewmodel.SettingsViewModel
import com.example.project.platform.DeviceInfo
import com.example.project.platform.BatteryInfo
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(viewModel: SettingsViewModel, onBack: () -> Unit) {
    val name by viewModel.userName.collectAsState()
    val isNewest by viewModel.sortByNewest.collectAsState()
    var tempName by remember { mutableStateOf(name) }

    val deviceInfo: DeviceInfo = koinInject()
    val batteryInfo: BatteryInfo = koinInject()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pengaturan", color = TextHeading) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = PrimaryTeal)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = BgMain)
            )
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().background(BgMain).padding(padding).padding(24.dp)) {
            Text(text = "Profil", fontSize = 18.sp, color = TextHeading, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = tempName,
                onValueChange = { tempName = it },
                label = { Text("Ubah Nama Panggilan") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryTeal,
                    unfocusedContainerColor = CardWhite,
                    focusedContainerColor = CardWhite
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(text = "Urutan Catatan", fontSize = 18.sp, color = TextHeading, fontWeight = FontWeight.Bold)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Urutkan dari yang terbaru", modifier = Modifier.weight(1f), color = TextBody)
                Switch(
                    checked = isNewest,
                    onCheckedChange = { viewModel.toggleSortOrder(it) },
                    colors = SwitchDefaults.colors(checkedThumbColor = PrimaryTeal, checkedTrackColor = PrimaryLight)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "Informasi Sistem", fontSize = 18.sp, color = TextHeading, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(12.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = CardWhite)
            ) {
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(40.dp).background(PrimaryLight, RoundedCornerShape(10.dp)), contentAlignment = Alignment.Center) {
                        Text("📱", fontSize = 20.sp)
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(text = deviceInfo.getDeviceName(), color = TextHeading, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Text(text = deviceInfo.getOsVersion(), color = TextBody, fontSize = 14.sp)
                        Text(text = "Versi App: ${deviceInfo.getAppVersion()}", color = TextBody, fontSize = 14.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            val batteryLevel = batteryInfo.getBatteryLevel()
            val isCharging = batteryInfo.isCharging()
            val batteryColor = if (isCharging) Color(0xFF2ECC71) else PrimaryTeal

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = CardWhite)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.size(40.dp).background(PrimaryLight, RoundedCornerShape(10.dp)), contentAlignment = Alignment.Center) {
                            Text(if (isCharging) "⚡" else "🔋", fontSize = 20.sp)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = "Kapasitas Baterai", color = TextHeading, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Text(text = if (isCharging) "Sedang mengisi daya" else "Digunakan", color = TextBody, fontSize = 14.sp)
                        }
                        Text(text = "$batteryLevel%", color = batteryColor, fontWeight = FontWeight.ExtraBold, fontSize = 18.sp)
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    LinearProgressIndicator(
                        progress = batteryLevel / 100f,
                        modifier = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(4.dp)),
                        color = batteryColor,
                        trackColor = LineDotted
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { viewModel.updateName(tempName); onBack() },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryTeal)
            ) {
                Text("Simpan Perubahan", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}