package de.pirrung.feature.blood.pressure.domain.use_case

import de.pirrung.feature.blood.pressure.domain.model.BloodPressure
import de.pirrung.feature.blood.pressure.domain.model.InvalidMeasurementException
import de.pirrung.feature.blood.pressure.domain.repository.BloodPressureRepository

class AddBloodPressureMeasurement(
    private val repository: BloodPressureRepository
) {

    @Throws(InvalidMeasurementException::class)
    suspend operator fun invoke(measurement: BloodPressure) {
        if (measurement.pulse == null)
            throw InvalidMeasurementException(message = "Puls darf nicht leer sein.")
        if (measurement.systolic == null)
            throw InvalidMeasurementException(message = "Systholischer Blutdruck darf nicht leer sein.")
        if (measurement.diastolic == null)
            throw InvalidMeasurementException(message = "Diastolischer Blutdruck darf nicht leer sein.")

        repository.insertBloodPressureMeasurement(measurement = measurement)
    }

}