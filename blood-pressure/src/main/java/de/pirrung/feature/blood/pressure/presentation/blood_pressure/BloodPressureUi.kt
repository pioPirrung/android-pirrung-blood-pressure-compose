package de.pirrung.feature.blood.pressure.presentation.blood_pressure

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import de.pirrung.feature.blood.pressure.presentation.blood_pressure.components.BloodPressureItem
import de.pirrung.feature.blood.pressure.presentation.theme.*
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
    val showMenu = remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                elevation = 0.dp,
                backgroundColor = Background,
                contentColor = Color.White,
                actions = {
                    IconButton(onClick = { showMenu.value = !showMenu.value }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
                    }
                    DropdownMenu(
                        modifier = modifier.background(BackgroundSecondary),
                        expanded = showMenu.value,
                        onDismissRequest = { showMenu.value = false }
                    ) {
                        DropdownMenuItem(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Filled.Refresh, contentDescription = null)
                        }
                        DropdownMenuItem(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Filled.Call, contentDescription = null)
                        }
                    }
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
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = modifier.weight(0.5f),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = "Durchschn. Blutdruck",
                        style = Typography.body1,
                        color = TextVariant
                    )
                    Text(
                        text = "${viewModel.avgState.value.systolic}/${viewModel.avgState.value.diastolic}",
                        style = Typography.h1,
                        color = TextSecondary
                    )
                }
            }
            LazyColumn {
                itemsIndexed(viewModel.state.value.measurements) { index, measurement ->
                    BloodPressureItem(
                        index = index,
                        bloodPressure = measurement,
                        onItemClicked = { navToBloodPressureDetail(measurement.id) }
                    )
                }
            }
        }
    }
}

