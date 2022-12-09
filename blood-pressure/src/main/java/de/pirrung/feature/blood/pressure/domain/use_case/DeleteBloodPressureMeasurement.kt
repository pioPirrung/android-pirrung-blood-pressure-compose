package de.pirrung.feature.blood.pressure.domain.use_case

import de.pirrung.feature.blood.pressure.domain.model.BloodPressure
import de.pirrung.feature.blood.pressure.domain.repository.BloodPressureRepository

class DeleteBloodPressureMeasurement(
    private val repository: BloodPressureRepository
) {

    suspend operator fun invoke(measurement: BloodPressure) {
        repository.deleteBloodPressureMeasurement(measurement = measurement)
    }
}