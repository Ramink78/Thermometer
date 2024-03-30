package rk.thermometer.ui.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseInCubic
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import rk.thermometer.ui.theme.ThermometerTheme
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.roundToInt
import kotlin.math.sin


@Composable
fun TemperatureMeter(
    modifier: Modifier = Modifier,
    minTemp: Int = -40,
    maxTemp: Int = 80,
    temperature: Int = 0
) {
    var oldTemperature by remember {
        mutableIntStateOf(temperature)
    }
    SideEffect {
        oldTemperature = temperature
    }
    val animatedTemperature = remember {
        Animatable(oldTemperature.toFloat())
    }
    val tempState = animatedTemperature.asState()
    LaunchedEffect(temperature) {
        animatedTemperature.animateTo(
            temperature.toFloat(),
            animationSpec = tween(
                durationMillis = 500,
               easing = EaseInCubic
            )
           )
    }
    val textMeasurer = rememberTextMeasurer()
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Canvas(
            modifier = Modifier
                .padding(1.dp)
                .width(150.dp)
                .height(250.dp)
        ) {
            val bigArcRadius = 30.dp.toPx()
            val cutoutDegree = 30f
            val topShapeWidth = cutoutWidth(cutoutDegree, bigArcRadius)
            val smallArcRadius = topShapeWidth / 2
            val columnLeft = (size.width - topShapeWidth) / 2
            val columnRight = columnLeft + topShapeWidth
            val bottomRect = Rect(
                center = Offset(x = size.width / 2, y = size.height - (bigArcRadius)),
                radius = bigArcRadius
            )
            val outlinePath = thermometerOutlinePath(
                leftLineX = columnLeft,
                rightLineX = columnRight,
                smallArcRadius = smallArcRadius,
                cutoutDegree = cutoutDegree,
                bigArcRadius = bigArcRadius
            )
            val fillPath = thermometerFillPath(
                leftLineX = columnLeft,
                rightLineX = columnRight,
                bigArcRadius = bigArcRadius,
                smallArcRadius = smallArcRadius,
                cutoutDegree = cutoutDegree,

                )
            drawThermometer(
                textMeasurer = textMeasurer,
                minTemp = minTemp,
                maxTemp = maxTemp,
                temperature = tempState.value.roundToInt(),
                outlinePath = outlinePath,
                fillPath = fillPath,
                columnRight = columnRight,
                bottomRect = bottomRect,
                smallArcRadius = smallArcRadius,
                tempDiv = 2
            )
        }
    }


}

fun DrawScope.drawFillPath(
    outlinePath: Path,
    top: Offset = Offset.Zero
) {
    clipPath(
        outlinePath
    ) {
        drawRect(
            color = Color(0xffe01e37),
            topLeft = top
        )
    }
}

private fun DrawScope.drawThermometer(
    textMeasurer: TextMeasurer,
    outlinePath: Path,
    fillPath: Path,
    maxTemp: Int,
    tempDiv: Int,
    minTemp: Int,
    temperature: Int,
    columnRight: Float,
    bottomRect: Rect,
    smallArcRadius: Float
) {
    val topOfMeter = Offset(
        x = columnRight + 8.dp.toPx(),
        y = smallArcRadius
    )
    val bottomOfMeter = Offset(
        x = columnRight + 8.dp.toPx(),
        y = bottomRect.top,
    )
    val height = bottomOfMeter.y - topOfMeter.y
    val tempRange = abs(minTemp) + abs(maxTemp)
    val tempUnits = tempRange / tempDiv
    val unitHeight = height / tempUnits
    drawFillPath(
        outlinePath = fillPath,
        top = Offset(
            0f,
            topOfMeter.y + (unitHeight) * (maxTemp - temperature) / tempDiv
        )
    )
    drawThermometerPath(
        outlinePath,
        strokeWidth = 2.dp
    )
    drawRuler(
        top = topOfMeter,
        bottom = bottomOfMeter,
        textMeasurer = textMeasurer,
        minTemp = minTemp,
        maxTemp = maxTemp,
        tempDiv = tempDiv
    )


}


private fun DrawScope.drawRuler(
    minTemp: Int = -40,
    maxTemp: Int = 80,
    tempDiv: Int = 2,
    verticalLineColor: Color = Color.LightGray,
    verticalLineWidth: Dp = 1.dp,
    horizontalLineHeight: Dp = 1.dp,
    horizontalLongLineColor: Color = Color.DarkGray,
    horizontalSmallLineColor: Color = Color.LightGray,
    top: Offset,
    bottom: Offset,
    textMeasurer: TextMeasurer,
    textColor: Color = Color.LightGray,
    textSize: TextUnit = 12.sp
) {
    val height = (bottom.y - top.y)
    val tempRange = abs(minTemp) + abs(maxTemp)
    val tempUnits = tempRange / tempDiv
    for (step in 0..tempUnits) {
        val length = if (step % 5 != 0 || step == 0) 4.dp else 8.dp
        val lineColor =
            if (step % 5 != 0 || step == 0) horizontalSmallLineColor else horizontalLongLineColor
        drawLine(
            color = lineColor,
            start = bottom - Offset(x = 0f, y = (step * (height / tempUnits))),
            end = bottom - Offset(
                x = -length.toPx(), y = (step * (height / tempUnits))
            ),
            strokeWidth = horizontalLineHeight.toPx(),
            cap = StrokeCap.Round
        )
        if (step % 15 == 0) drawText(
            textMeasurer = textMeasurer,
            text = ((step * tempDiv) + minTemp).toString(),
            topLeft = bottom - Offset(
                x = -10.dp.toPx(), y = (step * (height / tempUnits)) + 6.dp.toPx()
            ),
            style = TextStyle(
                color = textColor, fontSize = textSize, textAlign = TextAlign.Center
            ),
            size = Size(20.dp.toPx(), 20.dp.toPx())
        )

    }
    drawLine(
        color = verticalLineColor,
        start = top,
        end = bottom,
        cap = StrokeCap.Round,
        strokeWidth = verticalLineWidth.toPx()
    )


}

fun DrawScope.thermometerFillPath(
    leftLineX: Float,
    rightLineX: Float,
    smallArcRadius: Float,
    cutoutDegree: Float,
    bigArcRadius: Float,
    top: Float = 0f,
    padding: Dp = 5.dp
): Path {
    return Path().apply {
        arcTo(
            rect = Rect(
                left = leftLineX + padding.toPx(),
                top = top + padding.toPx(),
                right = rightLineX - padding.toPx(),
                bottom = (smallArcRadius * 2) - padding.toPx()
            ), startAngleDegrees = -180f, 180f, false
        )

        arcTo(
            rect = Rect(
                center = Offset(
                    x = (size.width / 2),
                    y = size.height - (bigArcRadius)
                ),
                radius = bigArcRadius - padding.toPx()
            ),
            forceMoveTo = false,
            startAngleDegrees = -90 + ((cutoutDegree - padding.toPx()) / 2),
            sweepAngleDegrees = 360 - cutoutDegree + padding.toPx()

        )
        lineTo(x = leftLineX + padding.toPx(), y = smallArcRadius + padding.toPx())

    }

}

fun DrawScope.thermometerOutlinePath(
    leftLineX: Float,
    rightLineX: Float,
    smallArcRadius: Float,
    cutoutDegree: Float,
    bigArcRadius: Float,
): Path {
    return Path().apply {
        arcTo(
            rect = Rect(
                left = leftLineX, top = 0f, right = rightLineX, bottom = smallArcRadius * 2
            ), startAngleDegrees = -180f, 180f, false
        )

        arcTo(
            rect = Rect(
                center = Offset(x = size.width / 2, y = size.height - (bigArcRadius)),
                radius = bigArcRadius
            ),
            forceMoveTo = false,
            startAngleDegrees = -90 + (cutoutDegree / 2),
            sweepAngleDegrees = 360 - cutoutDegree

        )
        lineTo(x = leftLineX, y = smallArcRadius)

    }

}

fun DrawScope.drawThermometerPath(
    path: Path,
    strokeWidth: Dp = Dp.Hairline
) {
    drawPath(
        path = path, Color.LightGray, style = Stroke(
            cap = StrokeCap.Round,
            width = strokeWidth.toPx()
        )
    )
}

private fun cutoutWidth(arcDegree: Float, radius: Float) =
    2 * (sin((arcDegree / 2) * PI / 180).toFloat() * radius)


@Preview
@Composable
private fun TemperatureComponentPreview() {
    ThermometerTheme {
        TemperatureMeter(
            temperature = 20
        )
    }
}