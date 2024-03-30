package rk.thermometer.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import rk.thermometer.ui.theme.ThermometerTheme

@Composable
fun HumiditySlider(
    modifier: Modifier = Modifier,
    state: AlarmSliderState = rememberAlarmSliderState(
        currentValue = 0f,
        range = 0..100
    ),
    submitButtonText: String = "Set",
    onSubmit: () -> Unit
) {
    Column(
        modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AlarmSlider(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            state = state,
            currentValueLabel = { value ->
                Text(text = "$value%")

            },
            indicatorLabel = { lableValue ->
                if (lableValue % 10 == 0)
                    Text(
                        text = "$lableValue%",
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = .5f)
                    )
            })
        OutlinedButton(
            onClick = onSubmit,
            modifier = Modifier.padding(vertical = 8.dp)

        ) {
            Text(text = submitButtonText)
        }
    }

}

@Preview
@Composable
private fun TemperatureSliderPreview() {
    ThermometerTheme(darkTheme = true) {
        HumiditySlider(
            onSubmit = {}
        )
    }
}