package de.pirrung.feature.blood.pressure.domain.use_case

import de.pirrung.feature.blood.pressure.domain.model.BloodPressure
import de.pirrung.feature.blood.pressure.domain.repository.BloodPressureRepository
import kotlinx.coroutines.flow.Flow

class GetFirstTenBloodPressureMeasurements(
    private val repository: BloodPressureRepository
) {

    operator fun invoke(): Flow<List<BloodPressure>> {
        return repository.getFirstTenBloodPressureMeasurements()
    }

}