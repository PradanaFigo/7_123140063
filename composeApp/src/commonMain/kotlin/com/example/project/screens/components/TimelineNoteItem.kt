package com.example.project.screens.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project.theme.*

@Composable
fun TimelineNoteItem(title: String, content: String, isFavorite: Boolean, isLast: Boolean, onClick: () -> Unit, onFavoriteToggle: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(40.dp)) {
            Spacer(modifier = Modifier.height(26.dp))
            Box(modifier = Modifier.size(14.dp).clip(CircleShape).background(PrimaryTeal))

            if (!isLast) {
                Canvas(modifier = Modifier.fillMaxHeight().padding(top = 6.dp).width(2.dp)) {
                    drawLine(
                        color = LineDotted,
                        start = Offset(size.width / 2, 0f),
                        end = Offset(size.width / 2, size.height),
                        strokeWidth = 3f,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                    )
                }
            }
        }
        Card(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp).clickable { onClick() },
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = CardWhite),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Row(modifier = Modifier.padding(20.dp), verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = TextHeading)
                    Text(text = content, fontSize = 13.sp, color = TextBody, maxLines = 1)
                }
                IconButton(onClick = onFavoriteToggle) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = null,
                        tint = if (isFavorite) SecondaryPeach else LineDotted // Peach hanya untuk ikon hati favorit
                    )
                }
            }
        }
    }
}