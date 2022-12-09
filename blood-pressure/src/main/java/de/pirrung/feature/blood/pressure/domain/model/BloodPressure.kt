package de.pirrung.feature.blood.pressure.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BloodPressure(
    @PrimaryKey val id: Int? = null,
    var systolic: Int? = null,
    var diastolic: Int? = null,
    var pulse: Int? = null,
    var timestamp: Long,
)

class InvalidMeasurementException(message: String): Exception(message)