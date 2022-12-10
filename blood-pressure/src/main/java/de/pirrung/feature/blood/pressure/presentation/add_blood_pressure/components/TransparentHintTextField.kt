package de.pirrung.feature.blood.pressure.presentation.add_blood_pressure.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import de.pirrung.feature.blood.pressure.presentation.theme.BackgroundSecondary
import de.pirrung.feature.blood.pressure.presentation.theme.Purple

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TransparentHintTextField(
    text: String,
    hint: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = TextStyle(),
    singleLine: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = modifier
            .padding(start = 15.dp, end = 15.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth(),
            value = text,
            onValueChange = onValueChange,
            label = {
                Text(text = hint, style = textStyle, color = Color.LightGray)
            },
            singleLine = singleLine,
            textStyle = textStyle,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.White,
                focusedBorderColor = Purple,
                unfocusedBorderColor = Purple,
                backgroundColor = BackgroundSecondary
            ),
            keyboardOptions = keyboardOptions,
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                })
        )
    }
}

@Preview
@Composable
private fun TextFieldPreview() {
    TransparentHintTextField(
        text = "Test",
        hint = "Systolisch",
        onValueChange = { }
    )
}