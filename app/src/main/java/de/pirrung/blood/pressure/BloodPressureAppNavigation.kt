package de.pirrung.blood.pressure

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.pirrung.feature.blood.pressure.presentation.add_blood_pressure.AddBloodPressureMeasurementScreen
import de.pirrung.feature.blood.pressure.presentation.blood_pressure.BloodPressureScreen
import de.pirrung.feature.blood.pressure.presentation.theme.Typography
import de.pirrung.feature.blood.pressure.presentation.util.Screen
import org.koin.androidx.compose.get

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
            BloodPressureScreen(
                viewModel = get(),
                navToBloodPressureDetail = {},
                navToAddBloodPressureMeasurement = { navController.navigate(Screen.AddBloodPressureMeasurementScreen.route) }
            )
        }
        composable(Screen.AddBloodPressureMeasurementScreen.route) {
            AddBloodPressureMeasurementScreen(
                viewModel = get(),
                onSaveClicked = { navController.navigateUp() }
            )
        }
    }
}