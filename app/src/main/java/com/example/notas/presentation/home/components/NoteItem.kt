package com.example.notas.presentation.home.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.notas.domain.models.Note

@Composable
fun NoteItem(
    note: Note,
    onClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
            .clickable {
                       onClick(note.id!!)
            },
    ){
        Column(modifier = Modifier.padding(8.dp)){
            Text(
                text = note.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = note.description?: "",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}