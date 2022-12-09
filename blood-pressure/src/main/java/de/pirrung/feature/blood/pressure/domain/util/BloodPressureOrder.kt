package de.pirrung.feature.blood.pressure.domain.util

sealed class BloodPressureOrder(
    val orderType: OrderType
) {
    class Date(orderType: OrderType) : BloodPressureOrder(orderType)
    class Pulse(orderType: OrderType) : BloodPressureOrder(orderType)
    class Systolic(orderType: OrderType) : BloodPressureOrder(orderType)
    class Diastolic(orderType: OrderType) : BloodPressureOrder(orderType)

    fun copy(orderType: OrderType): BloodPressureOrder {
        return when (this) {
            is Date -> Date(orderType)
            is Pulse -> Pulse(orderType)
            is Systolic -> Systolic(orderType)
            is Diastolic -> Diastolic(orderType)
        }
    }
}