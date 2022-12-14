package de.pirrung.feature.blood.pressure.presentation.blood_pressure

import de.pirrung.feature.blood.pressure.domain.model.BloodPressure
import de.pirrung.feature.blood.pressure.domain.util.BloodPressureOrder

sealed class BloodPressureEvent {
    data class Order(val order: BloodPressureOrder): BloodPressureEvent()
    data class ShowInfoSnackBar(val message: String = ""): BloodPressureEvent()
}