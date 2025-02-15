package sco.carlukesoftware.clockface.ui

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import sco.carlukesoftware.clockface.data.ClockStyle
import sco.carlukesoftware.clockface.ui.theme.ClockFaceTheme
import sco.carlukesoftware.clockface.utils.rememberCurrentTime
import java.time.LocalTime

/**
 * A composable function that draws a clock face with moving second and minute hands, and an hour label.
 *
 * The clock face consists of:
 * - A seconds dial, which rotates smoothly every second.
 * - A minutes dial, which rotates every minute.
 * - An hour label, which updates every hour.
 * - An overlay that indicates the current minute/second combination.
 *
 * @param modifier Modifier for styling and layout of the clock face. Defaults to a size of 420.dp.
 * @param clockStyle [ClockStyle] object that defines the visual style of the clock face components, including colors, text styles and sizes.
 * @param currentTime The current time, represented by a [LocalTime] object. Defaults to the current system time.
 *
 * @see ClockStyle
 * @see LocalTime
 * @see rememberCurrentTime
 */
@SuppressLint("DefaultLocale")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ClockFace(
    modifier: Modifier = Modifier
        .size(420.dp),
    clockStyle: ClockStyle = ClockStyle(),
    currentTime: LocalTime = rememberCurrentTime()
) {
    // Extract hour, minute, and second
    val currentHour = currentTime.hour
    val currentMinute = currentTime.minute
    val currentSecond = currentTime.second

    val textMeasurer = rememberTextMeasurer()

    // Use derivedStateOf for rotations based on time
    var secondRotation by remember {
        mutableFloatStateOf(0f)
    }
    var minuteRotation by remember {
        mutableFloatStateOf(0f)
    }
    var hour by remember {
        mutableIntStateOf(0)
    }

    // Calculate initial rotations based on current time
    secondRotation = -currentSecond * 6f
    minuteRotation = -currentMinute * 6f
    hour = currentHour

    // Update second hand rotation smoothly
    // secondRotation is updated by 6 degree clockwise every one second
    // here rotation is in negative, in order to get clockwise rotation
    LaunchedEffect(key1 = true) {
        while (true) {
            //in-order to get smooth transition we are updating rotation angle every 16ms
            //1000ms -> 6 degree
            //16ms -> 0.096
            delay(16)
            secondRotation -= 0.096f
        }
    }

    // Update minute hand rotation every second
    // minuteRotation is updated by 0.1 degree clockwise every one second
    // here rotation is in negative, in order to get clockwise rotation
    LaunchedEffect(key1 = true) {
        while (true) {
            delay(1000)
            minuteRotation -= 0.1f
        }
    }

    // Update hour label every hour
    // this will update hour label after every one hour
    LaunchedEffect(key1 = true) {
        while (true) {
            delay(60 * 60 * 1000L)
            hour = (hour + 1) % 24
        }
    }


    Canvas(
        modifier = modifier
    ) {
        val outerRadius = minOf(
            a = this.size.width,
            b = this.size.height
        ) / 2f
        val innerRadius = outerRadius - 60.dp.toPx()
        
        //Seconds Dial
        clockDial(
            radius = outerRadius,
            rotation = secondRotation,
            textMeasurer = textMeasurer,
            dialStyle = clockStyle
                .secondsDialStyle
        )

        //Minute Dial
        clockDial(
            radius = innerRadius,
            rotation = minuteRotation,
            textMeasurer = textMeasurer,
            dialStyle = clockStyle
                .minutesDialStyle
        )

        //draw hour
        val hourString = String.format("%02d", hour)
        val hourTextMeasureOutput = textMeasurer
            .measure(
                text = buildAnnotatedString {
                    append(hourString)
                },
                style = clockStyle
                    .hourLabelStyle
            )
        val hourTopLeft = Offset(
            x = this.center.x - (hourTextMeasureOutput.size.width / 2),
            y = this.center.y - (hourTextMeasureOutput.size.height / 2),
        )
        drawText(
            textMeasurer = textMeasurer,
            text = hourString,
            topLeft = hourTopLeft,
            style = clockStyle
                .hourLabelStyle
        )

        //drawing minute-second overlay
        val minuteHandOverlayPath = createMinuteHandOverlayPath(
            center = center,
            outerRadius = outerRadius,
            textMeasurer = textMeasurer,
            clockStyle = clockStyle,
            size = size
        )
        drawPath(
            path = minuteHandOverlayPath,
            color = clockStyle
                .overlayStrokeColor,
            style = Stroke(
                width = clockStyle
                    .overlayStrokeWidth.toPx()
            )
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
private fun ClockFacePreview() {
    ClockFaceTheme {
        ClockFace()
    }
}
