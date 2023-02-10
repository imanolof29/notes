package com.example.notas.presentation.home.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PriorityPoint(color: Color, title: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ){
        Text(title)
        Canvas(
            modifier = Modifier.fillMaxWidth(),
            onDraw = {
                drawCircle(
                    color, 16f
                )
            }
        )
    }
}