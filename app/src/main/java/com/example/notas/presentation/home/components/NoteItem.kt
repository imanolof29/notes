package com.example.notas.presentation.home.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.notas.data.local.entity.Priority
import com.example.notas.domain.models.Note
import com.example.notas.ui.theme.*

@Composable
fun NoteItem(
    note: Note,
    onClick: (Int) -> Unit,
    textStyle: TextStyle
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
            .clickable {
                onClick(note.id!!)
            },
    ){
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column{
                Text(
                    text = note.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = textStyle
                )
                Text(
                    text = note.description?: "",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = textStyle
                )
            }
            Box(
                modifier = Modifier.width(50.dp)
            ){
                when(note.priority) {
                    Priority.LOW -> PriorityPoint(color = green, title = "Baja")
                    Priority.MEDIUM -> PriorityPoint(color = yellow, title = "Media")
                    Priority.HIGH -> PriorityPoint(color = red, title = "Alta")
                }
            }
        }
    }
}