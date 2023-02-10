package com.example.notas.presentation.details

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notas.R
import com.example.notas.data.local.entity.Priority
import com.example.notas.util.toText
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
                elevation = 0.dp,
                backgroundColor = Color.White.compositeOver(Color.Black),
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
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                Text(
                    text = "Titulo de la nota",
                    fontSize = 18.sp
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.value.title,
                    onValueChange = {
                        viewModel.onEvent(DetailsEvent.OnTitleChange(it))
                    },
                    placeholder = { Text(state.value.titleHint) }
                )
            }
            Column(
                modifier = Modifier.padding(vertical = 10.dp)
            ) {
                Text(
                    text = "Describe la tarea",
                    fontSize = 18.sp
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.value.description,
                    onValueChange = {
                        viewModel.onEvent(DetailsEvent.OnDescriptionChange(it))
                    },
                    placeholder = { Text(state.value.descriptionHint) }
                )
            }
            Column(
                modifier = Modifier.padding(vertical = 10.dp)
            ) {
                Text(
                    text = "Elige la prioridad",
                    fontSize = 18.sp,
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.value.priority.toText(),
                    onValueChange = {},
                    readOnly = true,
                    leadingIcon = {
                        IconButton(
                            onClick = {
                                mExpanded = !mExpanded
                            }
                        ) {
                            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Down arrow")
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
                                        Text(it.toText())
                                    }
                                }
                            }
                        }
                    }
                )
            }

            Column(
                modifier = Modifier.padding(vertical = 16.dp)
            ){
                Text(
                    text = "Fecha limite",
                    fontSize = 18.sp,
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.value.dueDate.toString(),
                    onValueChange = {
                        print("Hello")
                    },
                    readOnly = true,
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
            }
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