package de.pirrung.feature.blood.pressure.presentation.blood_pressure_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import de.pirrung.feature.blood.pressure.presentation.theme.BackgroundSecondary
import de.pirrung.feature.blood.pressure.presentation.theme.Typography

@Suppress("LongParameterList", "FunctionNaming")
@Composable
fun BloodPressureDetailCard(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Center,
    valueTextStyle: TextStyle = Typography.h1,
    headerText: String,
    valueText: String,
    valueUnit: String
) {
    Card(
        modifier = modifier,
        elevation = 5.dp,
        backgroundColor = BackgroundSecondary,
        contentColor = Color.White,
    ) {
        Column(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp),
            verticalArrangement = verticalArrangement
        ) {
            Text(text = headerText, color = Color.LightGray, style = Typography.body1)
            Row {
                Text(text = valueText, color = Color.White, style = valueTextStyle)
                Text(
                    text = valueUnit,
                    color = Color.White,
                    style = Typography.body1,
                    modifier = Modifier.align(Alignment.Bottom)
                )
            }
        }
    }
}