package de.pirrung.feature.blood.pressure.presentation.blood_pressure_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

@Composable
fun BloodPressureDetailScreen(
    id: Int? = null,
    viewModel: BloodPressureDetailViewModel = get()
) {
    LaunchedEffect(key1 = true) {
        viewModel.viewModelScope.launch {
            id?.let {
                viewModel.getMeasurementById(id)
            }
        }
    }
    val measurementState = viewModel.measurementState.value

    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = measurementState.measurement?.systolic.toString())
    }
}