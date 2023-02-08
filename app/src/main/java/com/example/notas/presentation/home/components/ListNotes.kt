package com.example.notas.presentation.home.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notas.domain.models.Note
import com.example.notas.presentation.home.HomeEvent
import com.example.notas.presentation.home.HomeViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListNotes(
    notes: List<Note>,
    viewModel: HomeViewModel = hiltViewModel()
) {

    LazyColumn(modifier = Modifier.fillMaxSize()){
        items(
            items = notes,
        ){ note ->
            val dismissState = rememberDismissState(
                confirmStateChange = {
                    if(it == DismissValue.DismissedToStart) {
                        viewModel.onEvent(HomeEvent.OnDeleteItem(note))
                    }
                    true
                }
            )
            SwipeToDismiss(
                state = dismissState,
                directions = setOf(DismissDirection.EndToStart),

                background = {
                    val color by animateColorAsState(
                        when (dismissState.targetValue) {
                            DismissValue.Default -> Color.White
                            else -> Color.Red
                        }
                    )
                    val icon = Icons.Default.Delete
                    val scale by animateFloatAsState(
                        if (dismissState.targetValue == DismissValue.Default) 0.75f else 1f
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Icon(icon, contentDescription = "Delete icon", modifier = Modifier.scale(scale))
                    }
                },
                dismissContent = {
                    //NoteItem(note = note, onClick = navigate)
                }
            )
        }
    }
}