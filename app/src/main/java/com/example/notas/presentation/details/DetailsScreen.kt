package com.example.notas.presentation.details

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notas.R
import com.example.notas.data.local.entity.Priority
import com.example.notas.presentation.home.components.PriorityPoint
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailsScreen(
    onClose: () -> Unit,
    viewModel: DetailsViewModel = hiltViewModel()
) {

    val state = viewModel.state

    var mExpanded by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = state.value.shouldExit) {
        if (state.value.shouldExit) {
            onClose()
        }
    }

    val dateDialogState = rememberMaterialDialogState()

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
                    Box(
                        modifier = Modifier.clickable {
                            mExpanded = !mExpanded
                        }
                    ){
                        Text(viewModel.state.value.priority.toString())
                        DropdownMenu(
                            expanded = mExpanded,
                            onDismissRequest = { mExpanded = false},
                        ) {
                            enumValues<Priority>().forEach {
                                DropdownMenuItem(
                                    onClick = {
                                        mExpanded = false
                                        viewModel.onEvent(DetailsEvent.OnPriorityChange(it))
                                    }
                                ) {
                                    Text(it.toString())
                                }
                            }
                        }
                    }
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
                            dateDialogState.show()
                        }
                    ) {
                        Icon(painterResource(R.drawable.calendar), contentDescription = "Calendar")
                    }
                }
            )
            MaterialDialog(
                dialogState = dateDialogState,
                buttons = {
                    positiveButton(text = "Ok") {
                        Log.d("DATE", "OK")
                    }
                    negativeButton(text = "Cancelar")
                }
            ) {
                datepicker(
                    initialDate = state.value.dueDate,
                    title = "Elige una fecha",
                    allowedDateValidator = {
                        it.dayOfMonth % 2 == 1
                    }
                ) {
                    viewModel.onEvent(DetailsEvent.OnDateChange(it.atStartOfDay()))
                }
            }
        }
    }
}