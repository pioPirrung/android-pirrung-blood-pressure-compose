package de.pirrung.blood.pressure

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import de.pirrung.feature.blood.pressure.presentation.theme.TextPrimary
import de.pirrung.feature.blood.pressure.presentation.theme.TextSecondary
import de.pirrung.feature.blood.pressure.presentation.theme.Shapes
import de.pirrung.feature.blood.pressure.presentation.theme.TextVariant
import de.pirrung.feature.blood.pressure.presentation.theme.Background
import de.pirrung.feature.blood.pressure.presentation.theme.Typography

private val DarkColorPalette = darkColors(
    primary = TextPrimary,
    primaryVariant = TextSecondary,
    secondary = TextVariant,
    background = Background
)

@Composable
fun AndroidBloodPressureTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        DarkColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}