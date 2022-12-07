package de.pirrung.feature.blood.pressure.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BloodPressure(
    @PrimaryKey val id: Int? = null,
    val systolic: Int,
    val diastolic: Int,
    val pulse: Int,
    val timestamp: Long,
)

class InvalidMeasurementException(message: String): Exception(message)