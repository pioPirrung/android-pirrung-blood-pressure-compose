package de.pirrung.feature.blood.pressure.domain.use_case

import de.pirrung.feature.blood.pressure.domain.model.BloodPressure
import de.pirrung.feature.blood.pressure.domain.repository.BloodPressureRepository
import de.pirrung.feature.blood.pressure.domain.util.BloodPressureOrder
import de.pirrung.feature.blood.pressure.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetBloodPressureMeasurements(
    private val repository: BloodPressureRepository
) {

    operator fun invoke(
        order: BloodPressureOrder = BloodPressureOrder.Date(OrderType.Descending)
    ): Flow<List<BloodPressure>> {
        return repository.getBloodPressureMeasurements().map { measurements ->
            when (order.orderType) {
                is OrderType.Ascending -> {
                    when(order) {
                        is BloodPressureOrder.Date -> measurements.sortedBy { it.timestamp }
                        is BloodPressureOrder.Pulse -> measurements.sortedBy { it.pulse }
                        is BloodPressureOrder.Systolic -> measurements.sortedBy { it.systolic }
                        is BloodPressureOrder.Diastolic -> measurements.sortedBy { it.diastolic }
                    }
                }
                is OrderType.Descending -> {
                    when(order) {
                        is BloodPressureOrder.Date -> measurements.sortedByDescending { it.timestamp }
                        is BloodPressureOrder.Pulse -> measurements.sortedByDescending { it.pulse }
                        is BloodPressureOrder.Systolic -> measurements.sortedByDescending { it.systolic }
                        is BloodPressureOrder.Diastolic -> measurements.sortedByDescending { it.diastolic }
                    }
                }
            }
        }
    }

}