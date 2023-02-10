package com.example.notas.presentation.home


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.material.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notas.domain.NoteType
import com.example.notas.presentation.home.components.ListNotes
import com.example.notas.presentation.home.components.NoteItem
import com.example.notas.presentation.home.components.StatusCard
import com.example.notas.ui.theme.blue
import com.example.notas.ui.theme.grey
import com.example.notas.ui.theme.red


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navigate: (Int) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val state = viewModel.state

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Notas",
                        modifier = Modifier.fillMaxWidth(),
                    )
                },
                elevation = 0.dp,
                backgroundColor = Color.White.compositeOver(Color.Black)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navigate(-1)
                }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add note")
            }
        }
    ){

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ){
                StatusCard(
                    color = blue,
                    title = "Pendientes",
                    quantity = state.value.pendingNotes.size,
                    onClick = {
                        viewModel.onEvent(HomeEvent.OnNoteTypeChange(NoteType.PENDING))
                    }
                )
                StatusCard(
                    color = red,
                    title = "Caducadas",
                    quantity = state.value.expiredNotes.size,
                    onClick = {
                        viewModel.onEvent(HomeEvent.OnNoteTypeChange(NoteType.EXPIRED))
                    }
                )
                StatusCard(
                    color = grey,
                    title = "Completadas",
                    quantity = state.value.completedNotes.size,
                    onClick = {
                        viewModel.onEvent(HomeEvent.OnNoteTypeChange(NoteType.COMPLETED))
                    }
                )
            }

            when(state.value.noteType) {
                NoteType.PENDING -> {
                    Log.d("notes", state.value.pendingNotes.toString())
                    if(state.value.pendingNotes.isEmpty()){
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ){
                            Text("No hay notas pendientes")
                        }
                    }
                    ListNotes(notes = state.value.pendingNotes, navigate = navigate, textStyle = TextStyle(textDecoration = TextDecoration.None))
                }
                NoteType.EXPIRED -> {
                    Log.d("notes", state.value.expiredNotes.toString())
                    if(state.value.expiredNotes.isEmpty()){
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ){
                            Text("No hay notas caducadas")
                        }
                    }
                    ListNotes(notes = state.value.expiredNotes, navigate = navigate, textStyle = TextStyle(textDecoration = TextDecoration.None))
                }
                NoteType.COMPLETED -> {
                    Log.d("notes", state.value.completedNotes.toString())
                    if(state.value.completedNotes.isEmpty()){
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ){
                            Text("No hay notas completadas")
                        }
                    }
                    ListNotes(notes = state.value.completedNotes, navigate = navigate, textStyle = TextStyle(textDecoration = TextDecoration.LineThrough))
                }
            }

        }

    }
}