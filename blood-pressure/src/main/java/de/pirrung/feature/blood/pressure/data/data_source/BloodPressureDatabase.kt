package de.pirrung.feature.blood.pressure.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import de.pirrung.feature.blood.pressure.domain.model.BloodPressure

@Database(
    entities = [BloodPressure::class],
    version = 1,
    exportSchema = false
)
abstract class BloodPressureDatabase : RoomDatabase() {

    abstract val bloodPressureDao: BloodPressureDao

    companion object {
        const val DB_NAME = "blood_pressure_db"
    }

}