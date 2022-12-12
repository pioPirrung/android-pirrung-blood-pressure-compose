package de.pirrung.feature.blood.pressure.presentation.add_blood_pressure

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import de.pirrung.feature.blood.pressure.presentation.add_blood_pressure.components.TransparentHintTextField
import de.pirrung.feature.blood.pressure.presentation.blood_pressure_detail.BloodPressureDetailEvent
import de.pirrung.feature.blood.pressure.presentation.theme.Background
import de.pirrung.feature.blood.pressure.presentation.theme.Purple
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

@Composable
fun AddBloodPressureMeasurementScreen(
    viewModel: AddBloodPressureMeasurementViewModel = get(),
    onSaveClicked: () -> Unit,
    onBackClicked: () -> Unit
) {
    val systolicState = viewModel.systolicValue.value
    val diastolicState = viewModel.diastolicValue.value
    val pulseState = viewModel.pulseValue.value
    val noteState = viewModel.noteValue.value

    val scaffoldState = rememberScaffoldState()
    val focusManager = LocalFocusManager.current

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddBloodPressureMeasurementViewModel.UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }
                is AddBloodPressureMeasurementViewModel.UiEvent.SaveMeasurement -> {
                    onSaveClicked()
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                elevation = 0.dp,
                backgroundColor = Background,
                contentColor = Color.White,
                navigationIcon = {
                    IconButton(onClick = onBackClicked) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddBloodPressureMeasurementEvent.SaveMeasurement)
                },
                backgroundColor = Purple
            ) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "Messung speichern",
                    tint = Color.White
                )
            }
        },
        scaffoldState = scaffoldState
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    focusManager.clearFocus()
                }
        ) {
            TransparentHintTextField(
                text = systolicState.text,
                hint = systolicState.hint,
                onValueChange = {
                    viewModel.onEvent(
                        AddBloodPressureMeasurementEvent.EnteredSystolicValue(
                            it
                        )
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(16.dp))

            TransparentHintTextField(
                text = diastolicState.text,
                hint = diastolicState.hint,
                onValueChange = {
                    viewModel.onEvent(
                        AddBloodPressureMeasurementEvent.EnteredDiastolicValue(
                            it
                        )
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(16.dp))

            TransparentHintTextField(
                text = pulseState.text,
                hint = pulseState.hint,
                onValueChange = {
                    viewModel.onEvent(
                        AddBloodPressureMeasurementEvent.EnteredPulseValue(
                            it
                        )
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(16.dp))

            TransparentHintTextField(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(bottom = 10.dp),
                text = noteState.text,
                hint = noteState.hint,
                onValueChange = {
                    viewModel.onEvent(
                        AddBloodPressureMeasurementEvent.EnteredNote(
                            it
                        )
                    )
                }
            )
        }
    }

}