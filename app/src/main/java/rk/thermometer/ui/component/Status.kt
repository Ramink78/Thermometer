package rk.thermometer.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import rk.thermometer.ui.theme.ThermometerTheme

@Composable
fun TopBarStatus(
    modifier: Modifier = Modifier,
    isOnlineServer: Boolean = false,
    isOnlineYou: Boolean = true,

    ) {
    Row(
        modifier = modifier,
    ) {
        Row(modifier = Modifier.weight(.5f), horizontalArrangement = Arrangement.Center) {
            val espStatus = if (isOnlineServer) "Online" else "Offline"
            StatusIndicator(isOnline = isOnlineServer)
            Text(text = "Server is $espStatus", Modifier.padding(horizontal = 8.dp))

        }
        Row(modifier = Modifier.weight(.5f), horizontalArrangement = Arrangement.Center) {
            StatusIndicator(isOnline = isOnlineYou)
            val meStatus = if (isOnlineYou) "Online" else "Offline"
            Text(
                text = "You're $meStatus",
                Modifier.padding(horizontal = 8.dp)
            )

        }
    }

}

@Composable
fun StatusIndicator(
    isOnline: Boolean = true,
    size: Dp = 24.dp
) {
    Box(
        modifier = Modifier
            .size(size)
            .padding(2.dp)
            .drawBehind {
                drawCircle(
                    color = if (isOnline) Color.Green else Color.LightGray,
                    style = if (isOnline) Fill else Stroke(width = 1.dp.toPx(),
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f),
                        cap = StrokeCap.Round)
                )
            },
    )

}

@Preview
@Composable
private fun StatusIndicatorPreview() {
    ThermometerTheme {
        TopBarStatus()
    }
}