package de.pirrung.feature.blood.pressure.presentation.blood_pressure_detail

import de.pirrung.feature.blood.pressure.domain.model.BloodPressure

sealed class BloodPressureDetailEvent {
    data class LoadBloodPressureMeasurement(val id: Int) : BloodPressureDetailEvent()
    data class DeleteBloodPressureMeasurement(val measurement: BloodPressure) : BloodPressureDetailEvent()
}