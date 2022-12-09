package de.pirrung.feature.blood.pressure.presentation.blood_pressure.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.pirrung.feature.blood.pressure.domain.model.BloodPressure
import de.pirrung.feature.blood.pressure.presentation.theme.BackgroundSecondary
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BloodPressureItem(
    index: Int,
    bloodPressure: BloodPressure,
    onItemClicked: () -> Unit
) {
    val date: String = SimpleDateFormat("dd/MM/yyyy").format(bloodPressure.timestamp)
    val time: String = SimpleDateFormat("HH:mm").format(bloodPressure.timestamp)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .padding(start = 15.dp, end = 15.dp, top = 7.dp, bottom = 7.dp)
            .background(color = BackgroundSecondary),
        elevation = 5.dp,
        onClick = onItemClicked
    ) {
        Row(
            modifier = Modifier
                .background(BackgroundSecondary)
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(0.25f),
                text = index.toString(),
                color = Color.White
            )
            Text(
                modifier = Modifier.weight(0.3f),
                text = date,
                color = Color.White
            )
            Text(
                modifier = Modifier.weight(0.3f),
                text = time,
                color = Color.White
            )
            Text(
                modifier = Modifier.weight(0.15f),
                text = bloodPressure.systolic.toString(),
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
fun BloodPressureItemPreview() {
    BloodPressureItem(
        index = 0,
        bloodPressure = BloodPressure(
            id = 0,
            systolic = 128,
            diastolic = 100,
            pulse = 94,
            note = "Test",
            timestamp = Date().time
        ),
        onItemClicked = { }
    )
}