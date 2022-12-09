package de.pirrung.feature.blood.pressure.presentation.add_blood_pressure

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import de.pirrung.feature.blood.pressure.presentation.add_blood_pressure.components.TransparentHintTextField
import de.pirrung.feature.blood.pressure.presentation.theme.Purple
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.get

@Composable
fun AddBloodPressureMeasurementScreen(
    viewModel: AddBloodPressureMeasurementViewModel = get(),
    onSaveClicked: () -> Unit,
) {
    val systolicState = viewModel.systolicValue.value
    val diastolicState = viewModel.diastolicValue.value
    val pulseState = viewModel.pulseValue.value
    val noteState = viewModel.noteValue.value

    val scaffoldState = rememberScaffoldState()

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
                onFocusChange = {
                    viewModel.onEvent(
                        AddBloodPressureMeasurementEvent.ChangeSystolicFocus(
                            it
                        )
                    )
                },
                isHintVisible = systolicState.isHintVisible,
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
                onFocusChange = {
                    viewModel.onEvent(
                        AddBloodPressureMeasurementEvent.ChangeDiastolicFocus(
                            it
                        )
                    )
                },
                isHintVisible = diastolicState.isHintVisible,
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
                onFocusChange = {
                    viewModel.onEvent(
                        AddBloodPressureMeasurementEvent.ChangePulseFocus(
                            it
                        )
                    )
                },
                isHintVisible = pulseState.isHintVisible,
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(16.dp))

            TransparentHintTextField(
                modifier = Modifier.fillMaxHeight(),
                text = noteState.text,
                hint = noteState.hint,
                onValueChange = {
                    viewModel.onEvent(
                        AddBloodPressureMeasurementEvent.EnteredNote(
                            it
                        )
                    )
                },
                onFocusChange = {
                    viewModel.onEvent(
                        AddBloodPressureMeasurementEvent.ChangeNoteFocus(
                            it
                        )
                    )
                },
                isHintVisible = noteState.isHintVisible
            )
        }
    }

}