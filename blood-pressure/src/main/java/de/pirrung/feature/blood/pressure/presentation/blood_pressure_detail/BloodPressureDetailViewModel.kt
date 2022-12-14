package de.pirrung.feature.blood.pressure.presentation.blood_pressure_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.pirrung.feature.blood.pressure.domain.use_case.DeleteBloodPressureMeasurement
import de.pirrung.feature.blood.pressure.domain.use_case.GetBloodPressureMeasurement
import kotlinx.coroutines.launch

class BloodPressureDetailViewModel(
    private val getBloodPressureMeasurement: GetBloodPressureMeasurement,
    private val deleteBloodPressureMeasurement: DeleteBloodPressureMeasurement
) : ViewModel() {

    private val _measurementState = mutableStateOf(BloodPressureDetailState())
    val measurementState: State<BloodPressureDetailState> = _measurementState

    fun onEvent(event: BloodPressureDetailEvent) {
        when (event) {
            is BloodPressureDetailEvent.DeleteBloodPressureMeasurement -> {
                viewModelScope.launch {
                    deleteBloodPressureMeasurement.invoke(event.measurement)
                }
            }
            is BloodPressureDetailEvent.LoadBloodPressureMeasurement -> {
                viewModelScope.launch {
                    getBloodPressureMeasurement.invoke(event.id).also { bloodPressure ->
                        _measurementState.value =
                            measurementState.value.copy(measurement = bloodPressure)
                    }
                }
            }
        }
    }
}