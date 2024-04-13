package rk.thermometer.ui.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseInCubic
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import rk.thermometer.ui.theme.ThermometerTheme

@Composable
fun HumidityMeter(
    modifier: Modifier = Modifier,
    humidity: Float = 0f,
    outlineColor:Color
) {
    var oldHumidity by remember {
        mutableFloatStateOf(humidity)
    }
    SideEffect {
        oldHumidity = humidity
    }
    val animatedTemperature = remember {
        Animatable(oldHumidity)
    }
    val alpha by animateFloatAsState(
        targetValue = when (humidity) {
            in 90f..100f -> 1f
            in 80f..90f -> .9f
            in 70f..80f -> .8f
            in 60f..70f -> .7f
            in 50f..60f -> .6f
            in 40f..50f -> .5f
            in 30f..40f -> .4f
            else -> .3f
        }, label = "alphaAnimation"
    )
    val humState = animatedTemperature.asState()
    LaunchedEffect(humidity) {
        animatedTemperature.animateTo(
            humidity,
            animationSpec = tween(
                durationMillis = 500,
                easing = EaseInCubic
            )
        )
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val textMeasurer = rememberTextMeasurer()
        Canvas(
            modifier = Modifier
                .width(150.dp)
                .height(250.dp),

            ) {
            drawHumidityOutline(
                textMeasurer = textMeasurer,
                humidity = humState.value,
                outlineColor=outlineColor,
                fillColor = Color(0xff1982c4).copy(alpha=alpha)
            )

        }
    }

}

private fun DrawScope.drawHumidityOutline(
    textMeasurer: TextMeasurer,
    humidity: Float = 0f,
    outlineColor: Color= Color.DarkGray,
    fillColor: Color
) {
    val barHeight = size.height
    val division = 2
    val minHumidity = 0
    val maxHumidity = 100
    val barWidth = 40.dp.toPx()
    val topLeft = Offset(size.width / 2 - barWidth/2, 0f)
    val topRight = topLeft.copy(x = topLeft.x + barWidth)
    val rulerRect = Rect(
        topLeft = topLeft + Offset(y = barWidth / 2, x = 0f),
        bottomRight = Offset(y = barHeight - barWidth, x = topRight.x)
    )
    val unitCount = (maxHumidity - minHumidity) / division
    val unitHeight = rulerRect.height / unitCount
    drawHumidityFill(
        humidity = humidity,
        barWidth = barWidth,
        topLeft = rulerRect.bottomLeft + Offset(0f, barWidth),
        rulerRect = rulerRect,
        color = fillColor
    )
    drawRoundRect(
        topLeft = topLeft,
        color = outlineColor,
        size = Size(40.dp.toPx(), size.height),
        style = Stroke(width = 2.dp.toPx()),
        cornerRadius = CornerRadius(size.width / 2, size.width / 2)
    )
    for (unit in 0..unitCount) {
        val lineLength = if (unit % 10 == 0 && unit != unitCount) 10.dp.toPx() else 5.dp.toPx()
        val start = rulerRect.topRight + Offset(x = 0f, y = unitHeight * unit)
        val end = start.copy(x = start.x - lineLength)
        drawLine(
            outlineColor,
            start = start,
            end = end,
            strokeWidth = 1.dp.toPx(),
            cap = StrokeCap.Round
        )
        if (unit % 10 == 0) {
            //draw text
            drawText(
                textMeasurer = textMeasurer,
                text = "${maxHumidity - unit * division} %",
                topLeft = start + Offset(x = 8.dp.toPx(), -5.dp.toPx()),
                style = TextStyle(
                    color = outlineColor
                )
            )
        }
    }


}

private fun DrawScope.drawHumidityFill(
    humidity: Float,
    barWidth: Float,
    topLeft: Offset,
    rulerRect: Rect,
    barDivision: Int = 2,
    color:Color
) {
    val unitCount = 100 / barDivision
    val unitHeight = rulerRect.height / unitCount
    val height =
        when (humidity) {
            0f -> -barWidth
            100f ->-(unitHeight) * (humidity / barDivision) - (rulerRect.topLeft.y + barWidth)
            else -> -(unitHeight) * (humidity / barDivision)-barWidth
        }
    drawRoundRect(
        color = color,
        topLeft = topLeft,
        size = Size(
            width = barWidth,
            height = height
        ),
        cornerRadius = CornerRadius(barWidth / 2)
    )
}

@Preview
@Composable
private fun HumidityStatPreview() {
    ThermometerTheme {
        HumidityMeter(outlineColor = Color.DarkGray)
    }
}