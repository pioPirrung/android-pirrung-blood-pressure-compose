package de.pirrung.blood.pressure

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import de.pirrung.feature.blood.pressure.presentation.add_blood_pressure.AddBloodPressureMeasurementScreen
import de.pirrung.feature.blood.pressure.presentation.blood_pressure.BloodPressureScreen
import de.pirrung.feature.blood.pressure.presentation.blood_pressure_detail.BloodPressureDetailScreen
import de.pirrung.feature.blood.pressure.presentation.blood_pressure_detail.BloodPressureDetailViewModel
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
        composable(route = Screen.BloodPressureScreen.route) {
            BloodPressureScreen(
                viewModel = get(),
                navToBloodPressureDetail = { bloodPressureId ->
                    navController.navigate(
                        Screen.BloodPressureDetailScreen.route + "?bloodPressureId=$bloodPressureId"
                    )
                },
                navToAddBloodPressureMeasurement = {
                    navController.navigate(Screen.AddBloodPressureMeasurementScreen.route)
                }
            )
        }
        composable(route = Screen.AddBloodPressureMeasurementScreen.route) {
            AddBloodPressureMeasurementScreen(
                viewModel = get(),
                onSaveClicked = { navController.navigateUp() })
        }
        composable(
            route = Screen.BloodPressureDetailScreen.route + "?bloodPressureId={bloodPressureId}",
            arguments = listOf(navArgument(
                name = "bloodPressureId"
            ) {
                type = NavType.IntType
                defaultValue = -1
            })
        ) {
            val id = it.arguments?.getInt("bloodPressureId")
            BloodPressureDetailScreen(
                id = id,
                viewModel = get(),
                onBackClicked = { navController.navigateUp() }
            )
        }
    }
}