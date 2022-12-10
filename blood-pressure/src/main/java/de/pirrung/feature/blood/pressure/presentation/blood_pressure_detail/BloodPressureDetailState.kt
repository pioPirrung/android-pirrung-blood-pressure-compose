package de.pirrung.feature.blood.pressure.presentation.blood_pressure_detail

import de.pirrung.feature.blood.pressure.domain.model.BloodPressure

data class BloodPressureDetailState(
    val measurement: BloodPressure? = null
)