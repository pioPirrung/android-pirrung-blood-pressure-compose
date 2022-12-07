package de.pirrung.feature.blood.pressure.domain.repository

import de.pirrung.feature.blood.pressure.domain.model.BloodPressure
import kotlinx.coroutines.flow.Flow

interface BloodPressureRepository {

    fun getBloodPressureMeasurements() : Flow<List<BloodPressure>>

    suspend fun getBloodPressureMeasurementById(id: Int) : BloodPressure?

    suspend fun insertBloodPressureMeasurement(measurement: BloodPressure)

    suspend fun deleteBloodPressureMeasurement(measurement: BloodPressure)

}