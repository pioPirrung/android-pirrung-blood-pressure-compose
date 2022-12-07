package de.pirrung.blood.pressure

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.pirrung.feature.blood.pressure.presentation.theme.Typography
import de.pirrung.feature.blood.pressure.presentation.util.Screen

@Composable
fun BloodPressureAppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.BloodPressureScreen.route,
        modifier = modifier
    ) {
        composable(Screen.BloodPressureScreen.route) {
            Text(text = "Hallo Welt", style = Typography.h1)
        }
    }
}