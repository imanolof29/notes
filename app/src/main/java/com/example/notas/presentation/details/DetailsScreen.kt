package com.example.notas.presentation.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notas.R

@Composable
fun DetailsScreen(
    onClose: () -> Unit,
    viewModel: DetailsViewModel = hiltViewModel()
) {

    val state = viewModel.state

    LaunchedEffect(key1 = state.value.shouldExit) {
        if (state.value.shouldExit) {
            onClose()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { viewModel.onEvent(DetailsEvent.OnBackPressed) }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "back arrow"
                        )
                    }
                },
                title = {
                    Text(if (viewModel.state.value.id != -1) "Actualizar nota" else "Crear nota")
                },
                actions = {
                    IconButton(
                        onClick = {
                            if (viewModel.state.value.id != -1 || viewModel.state.value.id == null)
                                viewModel.onEvent(DetailsEvent.OnUpdate)
                            else
                                viewModel.onEvent(DetailsEvent.OnSave)
                        }) {
                        Icon(imageVector = Icons.Default.Check, contentDescription = "save")
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.value.title,
                onValueChange = {
                    viewModel.onEvent(DetailsEvent.OnTitleChange(it))
                },
                placeholder = { Text(state.value.titleHint) }
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.value.description,
                onValueChange = {
                    viewModel.onEvent(DetailsEvent.OnDescriptionChange(it))
                },
                placeholder = { Text(state.value.descriptionHint) }
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.value.dueDate.toString(),
                onValueChange = {
                    print("Hello")
                },
                enabled = false,
                placeholder = { Text(state.value.dueDate.toString()) },
                leadingIcon = {
                    IconButton(
                        onClick = {
                            viewModel.onEvent(DetailsEvent.OnDateClick)
                        }
                    ) {
                        Icon(painterResource(R.drawable.calendar), contentDescription = "Calendar")
                    }
                }
            )
        }
    }
}