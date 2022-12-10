package de.pirrung.feature.blood.pressure.presentation.add_blood_pressure

sealed class AddBloodPressureMeasurementEvent {
    data class EnteredSystolicValue(val value: String) : AddBloodPressureMeasurementEvent()
    data class EnteredDiastolicValue(val value: String) : AddBloodPressureMeasurementEvent()
    data class EnteredPulseValue(val value: String) : AddBloodPressureMeasurementEvent()
    data class EnteredNote(val value: String) : AddBloodPressureMeasurementEvent()
    object SaveMeasurement : AddBloodPressureMeasurementEvent()
}