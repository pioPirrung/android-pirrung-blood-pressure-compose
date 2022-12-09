package de.pirrung.feature.blood.pressure.presentation.blood_pressure

import de.pirrung.feature.blood.pressure.domain.model.BloodPressure
import de.pirrung.feature.blood.pressure.domain.util.BloodPressureOrder
import de.pirrung.feature.blood.pressure.domain.util.OrderType

data class BloodPressureState(
    val measurements: List<BloodPressure> = emptyList(),
    val order: BloodPressureOrder = BloodPressureOrder.Date(OrderType.Descending)
)