package de.pirrung.feature.blood.pressure.data.repository

import de.pirrung.feature.blood.pressure.data.data_source.BloodPressureDao
import de.pirrung.feature.blood.pressure.domain.model.BloodPressure
import de.pirrung.feature.blood.pressure.domain.repository.BloodPressureRepository
import kotlinx.coroutines.flow.Flow

class BloodPressureRepositoryImpl (
    private val bloodPressureDao: BloodPressureDao
) : BloodPressureRepository {
    override fun getBloodPressureMeasurements(): Flow<List<BloodPressure>> {
        return bloodPressureDao.getBloodPressureMeasurements()
    }

    override suspend fun getBloodPressureMeasurementById(id: Int): BloodPressure? {
        return bloodPressureDao.getBloodPressureMeasurementById(id = id)
    }

    override fun getFirstTenBloodPressureMeasurements(): Flow<List<BloodPressure>> {
        return bloodPressureDao.getFirstTenBloodPressureMeasurements()
    }

    override suspend fun insertBloodPressureMeasurement(measurement: BloodPressure) {
        bloodPressureDao.insertBloodPressureMeasurement(measurement = measurement)
    }

    override suspend fun deleteBloodPressureMeasurement(measurement: BloodPressure) {
        bloodPressureDao.deleteBloodPressureMeasurement(measurement = measurement)
    }


}