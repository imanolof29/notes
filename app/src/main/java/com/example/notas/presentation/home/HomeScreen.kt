package com.example.notas.presentation.home


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.rounded.Face
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.example.notas.presentation.details.DetailsEvent
import com.example.notas.presentation.home.components.NoteItem
import com.example.notas.util.Screen

@Composable
fun HomeScreen(
    navController: NavController,
    navigate: (Int) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val state = viewModel.state

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Notas")
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navigate(-1)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add note")
            }
        }
    ){

        if(state.value.notes.isEmpty()){
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Text("No hay notas")
            }
        }

        LazyColumn(modifier = Modifier.fillMaxWidth()){
            items(state.value.notes){ note ->
                NoteItem(note = note, navigate)
            }
        }
    }
}