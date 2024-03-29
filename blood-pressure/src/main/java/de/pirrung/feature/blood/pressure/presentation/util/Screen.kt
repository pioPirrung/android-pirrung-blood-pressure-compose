package de.pirrung.feature.blood.pressure.presentation.util

sealed class Screen(
    val route: String
) {
    object BloodPressureScreen: Screen("blood_pressure_screen")
    object BloodPressureDetailScreen: Screen("blood_pressure_detail_screen")
    object AddBloodPressureMeasurementScreen: Screen("add_blood_pressure_measurement_screen")
}
