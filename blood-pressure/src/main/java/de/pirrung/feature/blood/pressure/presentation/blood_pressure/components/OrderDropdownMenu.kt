package de.pirrung.feature.blood.pressure.presentation.blood_pressure.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import de.pirrung.feature.blood.pressure.domain.util.BloodPressureOrder
import de.pirrung.feature.blood.pressure.domain.util.OrderType

@Suppress("FunctionNaming")
@Composable
fun OrderDropdownMenu(
    modifier: Modifier = Modifier,
    bloodPressureOrder: BloodPressureOrder = BloodPressureOrder.Date(OrderType.Descending),
    onOrderClicked: (BloodPressureOrder) -> Unit
) {
    val showMenu = remember { mutableStateOf(false) }

    IconButton(onClick = { showMenu.value = !showMenu.value }) {
        Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
    }
    DropdownMenu(
        modifier = modifier,
        expanded = showMenu.value,
        onDismissRequest = { showMenu.value = false }
    ) {
        DropdownMenuItem(onClick = {
            onOrderClicked(bloodPressureOrder.copy(OrderType.Ascending))
        }) {
            Text(text = "Aufsteigend")
        }
        DropdownMenuItem(onClick = {
            onOrderClicked(bloodPressureOrder.copy(OrderType.Descending))
        }) {
            Text(text = "Absteigend")
        }
        DropdownMenuItem(onClick = {
            onOrderClicked(BloodPressureOrder.Date(bloodPressureOrder.orderType))
        }) {
            Text(text = "Datum")
        }
        DropdownMenuItem(onClick = {
            onOrderClicked(BloodPressureOrder.Systolic(bloodPressureOrder.orderType))
        }) {
            Text(text = "Systolisch")
        }
        DropdownMenuItem(onClick = {
            onOrderClicked(BloodPressureOrder.Diastolic(bloodPressureOrder.orderType))
        }) {
            Text(text = "Diastolisch")
        }
        DropdownMenuItem(onClick = {
            onOrderClicked(BloodPressureOrder.Pulse(bloodPressureOrder.orderType))
        }) {
            Text(text = "Puls")
        }
    }
}