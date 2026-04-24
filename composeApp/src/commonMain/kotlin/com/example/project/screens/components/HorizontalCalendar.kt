package com.example.project.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project.theme.*
import kotlinx.datetime.*

@Composable
fun HorizontalCalendar() {
    val today = remember { Clock.System.todayIn(TimeZone.currentSystemDefault()) }
    val currentEpochDays = today.toEpochDays()
    val startOfWeekEpoch = currentEpochDays - today.dayOfWeek.ordinal

    val dayNames = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        for (i in 0..6) {
            val d = LocalDate.fromEpochDays(startOfWeekEpoch + i)
            val isSelected = d == today

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = dayNames[i], fontSize = 12.sp, color = TextBody)
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(if (isSelected) TextHeading else Color.Transparent),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = d.dayOfMonth.toString(),
                        color = if (isSelected) Color.White else TextHeading,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}