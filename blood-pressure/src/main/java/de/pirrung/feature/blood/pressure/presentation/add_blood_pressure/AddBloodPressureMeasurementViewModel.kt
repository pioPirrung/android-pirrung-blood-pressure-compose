package de.pirrung.feature.blood.pressure.presentation.add_blood_pressure

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.pirrung.feature.blood.pressure.domain.model.BloodPressure
import de.pirrung.feature.blood.pressure.domain.model.InvalidMeasurementException
import de.pirrung.feature.blood.pressure.domain.use_case.AddBloodPressureMeasurement
import de.pirrung.feature.blood.pressure.domain.use_case.GetBloodPressureMeasurement
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class AddBloodPressureMeasurementViewModel(
    private val addBloodPressureMeasurement: AddBloodPressureMeasurement
) : ViewModel() {

    private val _systolicValue =
        mutableStateOf(BloodPressureTextFieldState(hint = "Systolischer Druck"))
    val systolicValue: State<BloodPressureTextFieldState> = _systolicValue

    private val _diastolicValue =
        mutableStateOf(BloodPressureTextFieldState(hint = "Diastolischer Druck"))
    val diastolicValue: State<BloodPressureTextFieldState> = _diastolicValue

    private val _pulseValue = mutableStateOf(BloodPressureTextFieldState(hint = "Puls"))
    val pulseValue: State<BloodPressureTextFieldState> = _pulseValue

    private val _noteValue =
        mutableStateOf(BloodPressureTextFieldState(hint = "Notiz"))
    val noteValue: State<BloodPressureTextFieldState> = _noteValue

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentBloodPressureId: Int? = null

//    init {
//        savedSateHandle.get<Int>("bloodPressureId")?.let { bloodPressureId ->
//            if (bloodPressureId != -1)
//                viewModelScope.launch {
//                    getBloodPressureMeasurement.invoke(bloodPressureId)?.also { bloodPressure ->
//                        currentBloodPressureId = bloodPressure.id
//                        _systolicValue.value = systolicValue.value.copy(
//                            text = bloodPressure.systolic.toString(),
//                            isHintVisible = false
//                        )
//                        _diastolicValue.value = diastolicValue.value.copy(
//                            text = bloodPressure.diastolic.toString(),
//                            isHintVisible = false
//                        )
//                        _pulseValue.value = pulseValue.value.copy(
//                            text = bloodPressure.pulse.toString(),
//                            isHintVisible = false
//                        )
//                        _noteValue.value = noteValue.value.copy(
//                            text = bloodPressure.note ?: "",
//                            isHintVisible = false
//                        )
//                    }
//                }
//        }
//    }

    fun onEvent(event: AddBloodPressureMeasurementEvent) {
        when (event) {
            is AddBloodPressureMeasurementEvent.EnteredSystolicValue -> {
                _systolicValue.value = systolicValue.value.copy(text = event.value)
            }
            is AddBloodPressureMeasurementEvent.EnteredDiastolicValue -> {
                _diastolicValue.value = diastolicValue.value.copy(text = event.value)
            }
            is AddBloodPressureMeasurementEvent.EnteredPulseValue -> {
                _pulseValue.value = pulseValue.value.copy(text = event.value)
            }
            is AddBloodPressureMeasurementEvent.EnteredNote -> {
                _noteValue.value = noteValue.value.copy(text = event.value)
            }
            is AddBloodPressureMeasurementEvent.SaveMeasurement -> {
                viewModelScope.launch {
                    try {
                        addBloodPressureMeasurement.invoke(
                            BloodPressure(
                                systolic = systolicValue.value.text.toInt(),
                                diastolic = diastolicValue.value.text.toInt(),
                                pulse = pulseValue.value.text.toInt(),
                                note = noteValue.value.text,
                                id = currentBloodPressureId,
                                timestamp = System.currentTimeMillis()
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveMeasurement)
                    } catch (e: InvalidMeasurementException) {
                        _eventFlow.emit(UiEvent.ShowSnackBar(
                            message = e.message ?: "Messung konnte nicht gespeichert werden"
                        ))
                    } catch (e: NumberFormatException) {
                        _eventFlow.emit(UiEvent.ShowSnackBar(
                            message = "Messung konnte nicht gespeichert werden. Überprüfen die Eingaben"
                        ))
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackBar(val message: String) : UiEvent()
        object SaveMeasurement : UiEvent()
    }
}
