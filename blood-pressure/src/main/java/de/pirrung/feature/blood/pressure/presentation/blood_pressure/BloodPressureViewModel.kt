package de.pirrung.feature.blood.pressure.presentation.blood_pressure

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.pirrung.feature.blood.pressure.domain.model.BloodPressure
import de.pirrung.feature.blood.pressure.domain.use_case.GetBloodPressureMeasurements
import de.pirrung.feature.blood.pressure.domain.use_case.GetFirstTenBloodPressureMeasurements
import de.pirrung.feature.blood.pressure.domain.util.BloodPressureOrder
import de.pirrung.feature.blood.pressure.domain.util.OrderType
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.Date

class BloodPressureViewModel(
    private val getBloodPressureMeasurements: GetBloodPressureMeasurements,
    private val getFirstTenBloodPressureMeasurements: GetFirstTenBloodPressureMeasurements
) : ViewModel() {

    private val _state = mutableStateOf(BloodPressureState())
    val state: State<BloodPressureState> = _state

    private val _avgState = mutableStateOf(
        BloodPressure(
            timestamp = Date().time,
            pulse = 0,
            systolic = 0,
            diastolic = 0,
            note = ""
        )
    )
    val avgState: State<BloodPressure> = _avgState

    private val _eventFlow = MutableSharedFlow<BloodPressureEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var getMeasurementsJob: Job? = null
    private var getFirstTenMeasurementsJob: Job? = null

    init {
        getMeasurements(BloodPressureOrder.Date(OrderType.Descending))
        getFirstTenMeasurements()
    }

    fun onEvent(event: BloodPressureEvent) {
        when (event) {
            is BloodPressureEvent.Order -> {
                if (state.value.order::class == event.order::class
                    && state.value.order.orderType == event.order.orderType
                ) {
                    return
                }
                getMeasurements(event.order)
            }
            is BloodPressureEvent.ShowInfoSnackBar -> {
                viewModelScope.launch {
                    _eventFlow.emit(
                        BloodPressureEvent.ShowInfoSnackBar(
                            message = "Der durchschnittliche Blutdruck wird anhand deiner letzten 10 Messungen berechnet."
                        )
                    )
                }
            }
        }
    }

    private fun getMeasurements(order: BloodPressureOrder) {
        getMeasurementsJob?.cancel()
        getMeasurementsJob = getBloodPressureMeasurements.invoke(order).onEach { measurements ->
            _state.value = state.value.copy(
                measurements = measurements,
                order = order
            )
        }.launchIn(viewModelScope)
    }

    private fun getFirstTenMeasurements() {
        getFirstTenMeasurementsJob?.cancel()
        getFirstTenMeasurementsJob =
            getFirstTenBloodPressureMeasurements.invoke().onEach { measurements ->
                val tmpMeasurement = BloodPressure(
                    timestamp = Date().time,
                    pulse = 0,
                    systolic = 0,
                    diastolic = 0,
                    note = "Avg Test"
                )

                measurements.forEach { item ->
                    tmpMeasurement.pulse = tmpMeasurement.pulse!! + item.pulse!!
                    tmpMeasurement.systolic = tmpMeasurement.systolic!! + item.systolic!!
                    tmpMeasurement.diastolic = tmpMeasurement.diastolic!! + item.diastolic!!
                }

                val divider = if (measurements.isEmpty()) 1 else measurements.size
                _avgState.value = avgState.value.copy(
                    timestamp = tmpMeasurement.timestamp,
                    systolic = tmpMeasurement.systolic!! / divider,
                    diastolic = tmpMeasurement.diastolic!! / divider,
                    pulse = tmpMeasurement.pulse!! / divider,
                    note = tmpMeasurement.note
                )
            }.launchIn(viewModelScope)
    }

}