package rk.thermometer.ui.temperature

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import rk.thermometer.ui.component.TemperatureMeter
import rk.thermometer.ui.component.TemperatureSlider
import rk.thermometer.ui.theme.ThermometerTheme

@Composable
private fun TemperatureScreenStateless(
    modifier: Modifier = Modifier,
    temperature: Float
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier.verticalScroll(scrollState),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            modifier = Modifier.padding(8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TemperatureMeter(
                    temperature = temperature,
                    outlineColor = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    modifier = Modifier.padding(end = 32.dp),
                    text = "$temperature°",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 42.sp,
                )
            }
        }
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            modifier = Modifier.padding(8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow)
        ) {
            Text(
                text = "Temperature Alarm",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            TemperatureSlider(onSubmit = {})
        }


    }

}

@Composable
fun TemperatureScreen(modifier: Modifier) {
    val viewModel: TemperatureScreenViewModel = hiltViewModel()
    val temperature by viewModel.tempFlow.collectAsState()
    TemperatureScreenStateless(
        temperature = temperature.value.toFloat(),
        modifier = modifier
    )

}

@Preview
@Composable
private fun TemperatureScreenPreview() {
    ThermometerTheme(darkTheme = true) {
        TemperatureScreenStateless(
            modifier = Modifier.fillMaxSize(),
            temperature = 48f
        )
    }
}