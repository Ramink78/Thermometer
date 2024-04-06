package rk.thermometer.ui.home

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Thermostat
import androidx.compose.material.icons.rounded.WaterDrop
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import rk.thermometer.R
import rk.thermometer.ui.component.TopBarStatus
import rk.thermometer.ui.humidity.HumidityScreen
import rk.thermometer.ui.temperature.TemperatureScreen
import rk.thermometer.ui.theme.ThermometerTheme

const val TEMPERATURE_SCREEN = "Temperature"
const val HUMIDITY_SCREEN = "Humidity"

@Composable
fun ThermometerApp() {
    val navController = rememberNavController()
    var currentDest by remember {
        mutableStateOf(TEMPERATURE_SCREEN)
    }
    Scaffold(
        bottomBar = {
            BottomBar(
                onHumiditySelected = {
                    navController.navigate(HUMIDITY_SCREEN)
                    currentDest= HUMIDITY_SCREEN
                },
                onTemperatureSelected = {
                    navController.navigate(TEMPERATURE_SCREEN)
                    currentDest= TEMPERATURE_SCREEN

                },
                currentDest = currentDest
            )
        },
        topBar = {
            TopBarStatus(modifier = Modifier
                .fillMaxWidth()
                .systemBarsPadding()
                .padding(top = 30.dp))
        }
    ) { paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            startDestination = TEMPERATURE_SCREEN
        ) {
            composable(TEMPERATURE_SCREEN) {
                TemperatureScreen(
                    modifier = Modifier.fillMaxSize()
                )

            }
            composable(HUMIDITY_SCREEN) {
                HumidityScreen(
                    modifier = Modifier.fillMaxSize()
                )
            }

        }

    }
}

@Composable
fun BottomBar(
    onTemperatureSelected: () -> Unit = {},
    onHumiditySelected: () -> Unit = {},
    currentDest: String
) {
    NavigationBar {
        NavigationBarItem(
            selected = currentDest == TEMPERATURE_SCREEN,
            onClick = onTemperatureSelected,
            icon = {
                Icon(
                    imageVector = Icons.Rounded.Thermostat,
                    contentDescription = stringResource(R.string.temperature)
                )
            },
            label = {
                Text(text = stringResource(R.string.temperature))
            }
        )
        NavigationBarItem(
            selected = currentDest == HUMIDITY_SCREEN,
            onClick = onHumiditySelected,
            icon = {
                Icon(
                    imageVector = Icons.Rounded.WaterDrop,
                    contentDescription = stringResource(R.string.humidity)
                )
            },
            label = {
                Text(text = stringResource(R.string.humidity))
            }
        )
    }


}

@Preview
@Composable
private fun BottomBarPreview() {
    ThermometerTheme(darkTheme = true) {
        ThermometerApp()

    }
}