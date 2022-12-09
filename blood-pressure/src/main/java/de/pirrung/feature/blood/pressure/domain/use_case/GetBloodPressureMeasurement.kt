package de.pirrung.feature.blood.pressure.domain.use_case

import de.pirrung.feature.blood.pressure.domain.model.BloodPressure
import de.pirrung.feature.blood.pressure.domain.repository.BloodPressureRepository

class GetBloodPressureMeasurement(
    private val repository: BloodPressureRepository
) {

    suspend operator fun invoke(id: Int): BloodPressure? {
        return repository.getBloodPressureMeasurementById(id = id)
    }

}