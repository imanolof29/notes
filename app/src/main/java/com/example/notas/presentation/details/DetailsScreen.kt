package com.example.notas.presentation.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DetailsScreen(
    onClose: () ->Unit,
    viewModel: DetailsViewModel = hiltViewModel()
){
    
    val state = viewModel.state

    LaunchedEffect(key1 = state.value.shouldExit){
        if(state.value.shouldExit){
            onClose()
        }
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { viewModel.onEvent(DetailsEvent.OnBackPressed) }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back arrow")
                    }
                },
                title = {
                    Text("Detalles")
                },
                actions = {
                    IconButton(onClick = { viewModel.onEvent(DetailsEvent.OnSave) }) {
                        Icon(imageVector = Icons.Default.Check, contentDescription = "save")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ){
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.value.title,
                onValueChange = {
                    viewModel.onEvent(DetailsEvent.OnTitleChange(it))
                },
                placeholder = { Text(state.value.titleHint) }
            )
            TextField(
                modifier = Modifier.fillMaxSize(),
                value = state.value.description,
                onValueChange = {
                    viewModel.onEvent(DetailsEvent.OnDescriptionChange(it))
                },
                placeholder = { Text(state.value.descriptionHint) }
            )
        }
    }

    
}