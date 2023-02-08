package com.example.notas.presentation.home.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun PriorityPoint(color: Color, title: String) {
    Row(){
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