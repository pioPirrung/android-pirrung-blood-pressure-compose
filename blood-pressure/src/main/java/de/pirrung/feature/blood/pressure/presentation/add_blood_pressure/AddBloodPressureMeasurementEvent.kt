package de.pirrung.feature.blood.pressure.presentation.add_blood_pressure

import androidx.compose.ui.focus.FocusState
import de.pirrung.feature.blood.pressure.domain.use_case.AddBloodPressureMeasurement

sealed class AddBloodPressureMeasurementEvent {
    data class EnteredSystolicValue(val value: String) : AddBloodPressureMeasurementEvent()
    data class EnteredDiastolicValue(val value: String) : AddBloodPressureMeasurementEvent()
    data class EnteredPulseValue(val value: String) : AddBloodPressureMeasurementEvent()
    data class ChangeSystolicFocus(val focusState: FocusState) : AddBloodPressureMeasurementEvent()
    data class ChangeDiastolicFocus(val focusState: FocusState) : AddBloodPressureMeasurementEvent()
    data class ChangePulseFocus(val focusState: FocusState) : AddBloodPressureMeasurementEvent()
    data class EnteredNote(val value: String) : AddBloodPressureMeasurementEvent()
    data class ChangeNoteFocus(val focusState: FocusState) : AddBloodPressureMeasurementEvent()
    object SaveMeasurement : AddBloodPressureMeasurementEvent()
}