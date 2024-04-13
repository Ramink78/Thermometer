package rk.thermometer.ui.humidity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import rk.thermometer.ui.component.HumidityMeter
import rk.thermometer.ui.component.HumiditySlider

@Composable
private fun HumidityScreenStateless(
    humidity: Float,
    lastUpdate: String,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier.verticalScroll(scrollState),
    ) {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            modifier = Modifier.padding(8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(vertical = 20.dp),
            ) {
                HumidityMeter(
                    humidity = humidity,
                    outlineColor = MaterialTheme.colorScheme.onSurface
                )
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        modifier = Modifier
                            .padding(end = 32.dp)
                            .align(Alignment.CenterHorizontally),
                        text = "$humidity %",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 42.sp,
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        modifier =
                        Modifier
                            .padding(end = 8.dp)
                            .align(Alignment.End),
                        text = "Last checked: $lastUpdate",
                        fontWeight = FontWeight.Thin,
                        fontSize = 10.sp
                    )
                }
            }
        }
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            modifier = Modifier.padding(8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow)
        ) {
            Text(
                text = "Humidity Alarm",
                modifier = Modifier.padding(12.dp),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            HumiditySlider(onSubmit = {})
        }

    }
}

@Composable
fun HumidityScreen(modifier: Modifier) {
    val viewModel: HumidityScreenViewModel = hiltViewModel()
    val humidity by viewModel.humFlow.collectAsState()
    HumidityScreenStateless(
        humidity = humidity.value.toFloat(),
        modifier = modifier,
        lastUpdate = humidity.lastUpdate
    )
}