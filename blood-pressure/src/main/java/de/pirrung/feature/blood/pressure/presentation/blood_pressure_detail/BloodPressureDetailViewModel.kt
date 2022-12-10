package de.pirrung.feature.blood.pressure.presentation.blood_pressure_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.pirrung.feature.blood.pressure.domain.use_case.GetBloodPressureMeasurement
import kotlinx.coroutines.launch

class BloodPressureDetailViewModel(
    private val getBloodPressureMeasurement: GetBloodPressureMeasurement
) : ViewModel() {

    private val _measurementState = mutableStateOf(BloodPressureDetailState())
    val measurementState: State<BloodPressureDetailState> = _measurementState

    fun getMeasurementById(id: Int) {
        if (id != -1)
            viewModelScope.launch {
                getBloodPressureMeasurement.invoke(id)?.also { bloodPressure ->
                    _measurementState.value =
                        measurementState.value.copy(measurement = bloodPressure)
                }
            }
    }
}