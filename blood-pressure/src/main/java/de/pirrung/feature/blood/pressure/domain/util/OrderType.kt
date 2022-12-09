package de.pirrung.feature.blood.pressure.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}