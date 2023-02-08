package com.example.notas.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StatusCard(
    color: Color,
    title: String,
    quantity: Int,
    onClick: () -> Unit
) {

    Card(
        elevation = 0.dp,
        backgroundColor = color.copy(alpha = 0.2f),
        modifier = Modifier.clickable {
            onClick()
        }
    ){
        Column(
            modifier = Modifier
                .padding(20.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                color = color,
                fontSize = 14.sp
            )
            Text(
                text = quantity.toString(),
                color = color,
                fontSize = 28.sp
            )
        }
    }
}