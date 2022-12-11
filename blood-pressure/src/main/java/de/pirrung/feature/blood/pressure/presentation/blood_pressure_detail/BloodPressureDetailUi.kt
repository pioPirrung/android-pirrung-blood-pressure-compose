package de.pirrung.feature.blood.pressure.presentation.blood_pressure_detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import de.pirrung.feature.blood.pressure.presentation.blood_pressure_detail.components.BloodPressureDetailCard
import de.pirrung.feature.blood.pressure.presentation.theme.Background
import de.pirrung.feature.blood.pressure.presentation.theme.BackgroundSecondary
import de.pirrung.feature.blood.pressure.presentation.theme.Typography
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

@Composable
fun BloodPressureDetailScreen(
    modifier: Modifier = Modifier,
    id: Int? = null,
    viewModel: BloodPressureDetailViewModel = get(),
    onBackClicked: () -> Unit
) {
    LaunchedEffect(key1 = true) {
        viewModel.viewModelScope.launch {
            id?.let {
                viewModel.getMeasurementById(id)
            }
        }
    }
    val measurementState = viewModel.measurementState.value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                elevation = 0.dp,
                backgroundColor = Background,
                contentColor = Color.White,
                navigationIcon = {
                    IconButton(onClick = onBackClicked) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(1.0f)
                    .padding(top = 10.dp)
            ) {
                BloodPressureDetailCard(
                    modifier = Modifier
                        .weight(0.5f)
                        .padding(start = 15.dp)
                        .fillMaxHeight(0.15f),
                    headerText = "Systolisch",
                    valueText = measurementState.measurement?.systolic.toString(),
                    valueUnit = "mmHg"
                )
                BloodPressureDetailCard(
                    modifier = Modifier
                        .weight(0.5f)
                        .padding(start = 15.dp, end = 15.dp)
                        .fillMaxHeight(0.15f),
                    headerText = "Diastolisch",
                    valueText = measurementState.measurement?.diastolic.toString(),
                    valueUnit = "mmHg"
                )
            }
            BloodPressureDetailCard(
                modifier = Modifier
                    .padding(start = 15.dp, end = 15.dp)
                    .fillMaxHeight(0.18f)
                    .fillMaxWidth(),
                headerText = "Puls",
                valueText = measurementState.measurement?.pulse.toString(),
                valueUnit = "bpm"
            )
            if(measurementState.measurement?.note?.isNotBlank() == true)
                BloodPressureDetailCard(
                    modifier = Modifier
                        .padding(start = 15.dp, end = 15.dp, bottom = 15.dp)
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Top,
                    valueTextStyle = Typography.body2,
                    headerText = "Notiz",
                    valueText = measurementState.measurement.note!!,
                    valueUnit = ""
                )
            else
                BloodPressureDetailCard(
                    modifier = Modifier
                        .padding(start = 15.dp, end = 15.dp, bottom = 15.dp)
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Top,
                    valueTextStyle = Typography.body2,
                    headerText = "Notiz",
                    valueText = "Du hast bei dieser Messung keine Notiz hinzugefügt.",
                    valueUnit = ""
                )
        }
    }
}