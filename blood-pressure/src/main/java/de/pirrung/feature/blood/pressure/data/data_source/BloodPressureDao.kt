package de.pirrung.feature.blood.pressure.data.data_source

import androidx.room.Query
import androidx.room.Insert
import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Delete
import de.pirrung.feature.blood.pressure.domain.model.BloodPressure
import kotlinx.coroutines.flow.Flow

@Dao
interface BloodPressureDao {

    @Query("SELECT * FROM bloodpressure")
    fun getBloodPressureMeasurements(): Flow<List<BloodPressure>>

    @Query("SELECT * FROM bloodpressure WHERE id = :id")
    suspend fun getBloodPressureMeasurementById(id: Int): BloodPressure?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBloodPressureMeasurement(measurement: BloodPressure)

    @Delete
    suspend fun deleteBloodPressureMeasurement(measurement: BloodPressure)

}