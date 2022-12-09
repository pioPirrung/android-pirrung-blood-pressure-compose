package de.pirrung.feature.blood.pressure.presentation.blood_pressure

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.pirrung.feature.blood.pressure.domain.model.BloodPressure
import de.pirrung.feature.blood.pressure.domain.use_case.AddBloodPressureMeasurement
import de.pirrung.feature.blood.pressure.domain.use_case.DeleteBloodPressureMeasurement
import de.pirrung.feature.blood.pressure.domain.use_case.GetBloodPressureMeasurements
import de.pirrung.feature.blood.pressure.domain.use_case.GetFirstTenBloodPressureMeasurements
import de.pirrung.feature.blood.pressure.domain.util.BloodPressureOrder
import de.pirrung.feature.blood.pressure.domain.util.OrderType
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.Date

class BloodPressureViewModel(
    private val addBloodPressureMeasurement: AddBloodPressureMeasurement,
    private val deleteBloodPressureMeasurement: DeleteBloodPressureMeasurement,
    private val getBloodPressureMeasurements: GetBloodPressureMeasurements,
    private val getFirstTenBloodPressureMeasurements: GetFirstTenBloodPressureMeasurements
) : ViewModel() {

    private val _state = mutableStateOf(BloodPressureState())
    val state: State<BloodPressureState> = _state

    val averageBloodPressureMeasurement: BloodPressure =
        BloodPressure(timestamp = Date().time, pulse = 0, systolic = 0, diastolic = 0)
    private var recentlyDeletedMeasurement: BloodPressure? = null
    private var getMeasurementsJob: Job? = null
    private var getFirstTenMeasurementsJob: Job? = null

    init {
        getMeasurements(BloodPressureOrder.Date(OrderType.Descending))
        getFirstTenMeasurements()
    }

    fun onEvent(event: BloodPressureEvent) {
        when (event) {
            is BloodPressureEvent.Order -> {
                if (state.value.order::class == event.order::class && state.value.order.orderType == event.order.orderType) return
                getMeasurements(event.order)
            }
            is BloodPressureEvent.DeleteBloodPressureMeasurement -> {
                viewModelScope.launch {
                    deleteBloodPressureMeasurement.invoke(event.measurement)
                    recentlyDeletedMeasurement = event.measurement
                }
            }
            is BloodPressureEvent.RestoreBloodPressureMeasurement -> {
                viewModelScope.launch {
                    addBloodPressureMeasurement.invoke(recentlyDeletedMeasurement ?: return@launch)
                    recentlyDeletedMeasurement = null
                }
            }
        }
    }

    private fun getMeasurements(order: BloodPressureOrder) {
        getMeasurementsJob?.cancel()
        getMeasurementsJob = getBloodPressureMeasurements.invoke(order).onEach { measurements ->
            _state.value = state.value.copy(measurements = measurements)
        }.launchIn(viewModelScope)
    }

    private fun getFirstTenMeasurements() {
        getFirstTenMeasurementsJob?.cancel()
        getFirstTenMeasurementsJob =
            getFirstTenBloodPressureMeasurements.invoke().onEach { measurements ->
                measurements.map { bloodPressure ->
                    averageBloodPressureMeasurement.apply {
                        this.pulse?.plus(bloodPressure.pulse!!)
                        this.systolic?.plus(bloodPressure.systolic!!)
                        this.diastolic?.plus(bloodPressure.diastolic!!)
                    }
                }
            }.launchIn(viewModelScope)
    }

}