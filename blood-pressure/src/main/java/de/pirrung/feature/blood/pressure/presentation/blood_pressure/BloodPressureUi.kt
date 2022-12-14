package de.pirrung.feature.blood.pressure.presentation.blood_pressure

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.IconButton
import androidx.compose.material.Icon
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import de.pirrung.feature.blood.pressure.presentation.blood_pressure.components.BloodPressureItem
import de.pirrung.feature.blood.pressure.presentation.blood_pressure.components.OrderDropdownMenu
import de.pirrung.feature.blood.pressure.presentation.theme.Background
import de.pirrung.feature.blood.pressure.presentation.theme.BackgroundSecondary
import de.pirrung.feature.blood.pressure.presentation.theme.TextSecondary
import de.pirrung.feature.blood.pressure.presentation.theme.Purple
import de.pirrung.feature.blood.pressure.presentation.theme.Typography
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.get

@Composable
fun BloodPressureScreen(
    viewModel: BloodPressureViewModel = get(),
    navToBloodPressureDetail: (Int?) -> Unit,
    navToAddBloodPressureMeasurement: () -> Unit
) {
    BloodPressureContent(
        viewModel = viewModel,
        navToBloodPressureDetail = navToBloodPressureDetail,
        navToAddBloodPressureMeasurement = navToAddBloodPressureMeasurement
    )
}

@Composable
private fun BloodPressureContent(
    viewModel: BloodPressureViewModel,
    navToBloodPressureDetail: (Int?) -> Unit,
    navToAddBloodPressureMeasurement: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scaffoldState = rememberScaffoldState()
    val bloodPressureState = viewModel.state.value
    val bloodPressureAvgState = viewModel.avgState.value

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is BloodPressureEvent.ShowInfoSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(message = event.message)
                }
                else -> {}
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                elevation = 0.dp,
                backgroundColor = Background,
                contentColor = Color.White,
                actions = {
                    OrderDropdownMenu(
                        modifier = Modifier.background(BackgroundSecondary),
                        bloodPressureOrder = bloodPressureState.order,
                        onOrderClicked = {
                            viewModel.onEvent(BloodPressureEvent.Order(it))
                        }
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navToAddBloodPressureMeasurement,
                backgroundColor = Purple,
                content = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Messung hinzufügen",
                        tint = Color.White
                    )
                }
            )

        },
        scaffoldState = scaffoldState
    ) { padding ->
        Column(
            modifier = modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Durchschn. Blutdruck",
                        style = Typography.body1,
                        color = TextSecondary
                    )
                    IconButton(
                        modifier = Modifier.align(Alignment.Top),
                        onClick = {
                            viewModel.onEvent(BloodPressureEvent.ShowInfoSnackBar())
                        }
                    ) {
                        Icon(
                            modifier = Modifier.align(Alignment.Top),
                            imageVector = Icons.Default.Info,
                            contentDescription = null
                        )
                    }
                }
                Text(
                    text = "${bloodPressureAvgState.systolic}/${bloodPressureAvgState.diastolic}",
                    style = Typography.h1,
                    color = TextSecondary
                )
            }
            LazyColumn {
                itemsIndexed(bloodPressureState.measurements) { index, measurement ->
                    BloodPressureItem(
                        index = index + 1,
                        bloodPressure = measurement,
                        onItemClicked = { navToBloodPressureDetail(measurement.id) }
                    )
                }
            }
        }
    }
}

