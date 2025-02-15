package sco.carlukesoftware.clockface.ui

import android.annotation.SuppressLint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.drawText
import androidx.compose.ui.unit.dp
import sco.carlukesoftware.clockface.data.ClockStyle
import sco.carlukesoftware.clockface.data.DialStyle
import sco.carlukesoftware.clockface.utils.calculateOffset
import sco.carlukesoftware.clockface.utils.measureRotatedTextSize
import kotlin.math.cos
import kotlin.math.sin

/**
 * Draws the dial of a clock, including the lines representing minutes/seconds and the numbers representing hours.
 *
 * @param radius The radius of the clock dial.
 * @param rotation The rotation angle of the dial in degrees. This allows for the dial to be rotated,
 *                 for example, to represent the current time.
 * @param textMeasurer The [TextMeasurer] used to measure the size of the text labels for the hour marks.
 * @param dialStyle The [DialStyle] object containing the styling properties for the dial, such as colors, line heights, and widths.
 *                  Defaults to [DialStyle] with default values.
 *
 * The function draws the following elements:
 *
 * - **Minute/Second Marks:** 60 lines are drawn around the circumference of the circle, representing the
 *   minutes and seconds.
 * - **Hour Marks:** Every fifth mark (at indices 0, 5, 10, etc.) is considered an hour mark. These marks are:
 *   - Drawn with a longer line than the regular minute/second marks, controlled by `fiveStepsLineHeight` in [DialStyle].
 *   - Labelled with a number, drawn using [drawStepLabel].
 *
 * The position of each mark (line) is calculated using trigonometric functions based on the `radius`, `rotation`, and the step index.
 * The `rotation` parameter allows for the entire dial to be rotated.
 *
 * The steps (lines) are drawn using [drawLine], and the labels are drawn using [drawStepLabel] (which is assumed to be defined elsewhere).
 * The function utilizes a `repeat` loop to iterate through each of the 60 steps.
 */
fun DrawScope.clockDial(
    radius: Float,
    rotation: Float,
    textMeasurer: TextMeasurer,
    dialStyle: DialStyle = DialStyle()
) {
    val stepsCount = 60
    val angleIncrement = 360f / stepsCount

    //this will draw 60 steps
    repeat(stepsCount) { stepIndex ->
        val stepAngle = stepIndex * angleIncrement
        val rotatedAngle = stepAngle + rotation
        val angleInRadians = Math.toRadians(rotatedAngle.toDouble())

        val isFiveStep = stepIndex % 5 == 0
        val lineHeight = if (isFiveStep) {
            dialStyle.fiveStepsLineHeight.toPx()
        } else {
            dialStyle.normalStepsLineHeight.toPx()
        }

        //calculate steps, start and end offset
        val startOffset = calculateOffset(
            center = center,
            radius = radius,
            angleInRadians = angleInRadians
        )
        val endOffset = calculateOffset(
            center = center,
            radius = radius - lineHeight,
            angleInRadians = angleInRadians
        )

        //draw step
        drawLine(
            color = dialStyle
                .stepsColor,
            start = startOffset,
            end = endOffset,
            strokeWidth = dialStyle
                .stepsWidth.toPx(),
            cap = StrokeCap.Round
        )

        //draw steps labels
        if (isFiveStep) {
            drawStepLabel(
                stepIndex = stepIndex,
                radius = radius,
                lineHeight = lineHeight,
                rotation = rotation,
                textMeasurer = textMeasurer,
                dialStyle = dialStyle,
                angleInRadians = angleInRadians
            )
        }
    }
}

/**
 * Draws a step label (number) around the dial's circumference.
 *
 * This function calculates the position of a step label based on its index,
 * the dial's radius, the desired label offset from the dial's edge, and the
 * step's angular position. It then draws the label, rotated to align with the
 * radial direction, using the provided [TextMeasurer] and [DialStyle].
 *
 * @param stepIndex The index of the step being labeled (e.g., 0 for the first step, 1 for the second, etc.). This value is formatted to be at least two digits.
 * @param radius The radius of the dial.
 * @param lineHeight The height of the lines (ticks) drawn around the dial. This is used to offset the label from the lines.
 * @param rotation The overall rotation applied to the dial in degrees. This affects the label's rotation.
 * @param textMeasurer The [TextMeasurer] used to calculate text layout and dimensions.
 * @param dialStyle The [DialStyle] containing styling information for the dial, including the text style and padding.
 * @param angleInRadians The angle of the step in radians, representing its position around the dial.
 */
@SuppressLint("DefaultLocale")
private fun DrawScope.drawStepLabel(
    stepIndex: Int,
    radius: Float,
    lineHeight: Float,
    rotation: Float,
    textMeasurer: TextMeasurer,
    dialStyle: DialStyle,
    angleInRadians: Double
) {
    val stepLabel = String.format("%02d", stepIndex)
    val textRotation = -(stepIndex * 6f) - rotation

    val labelRadius = radius - lineHeight - dialStyle.stepsLabelTopPadding.toPx()
    val labelOffset = calculateOffset(
        center = center,
        radius = labelRadius,
        angleInRadians = angleInRadians
    )

    drawRotatedText(
        text = stepLabel,
        style = dialStyle.stepsTextStyle,
        angleDegrees = textRotation,
        rotationCenter = labelOffset,
        textMeasurer = textMeasurer
    )
}

/**
 * Draws rotated text within the current [DrawScope].
 *
 * This function draws the given [text] with the specified [style] rotated by [angleDegrees]
 * around the [rotationCenter]. It calculates the necessary offset to center the rotated
 * text around the provided rotation center.
 *
 * @param text The text string to draw.
 * @param style The [TextStyle] to apply to the text.
 * @param angleDegrees The rotation angle in degrees (clockwise).
 * @param rotationCenter The [Offset] representing the center point for rotation.
 * @param textMeasurer The [TextMeasurer] used to measure the text's dimensions.
 *
 * @see DrawScope
 * @see TextMeasurer
 * @see TextStyle
 */
fun DrawScope.drawRotatedText(
    text: String,
    style: TextStyle,
    angleDegrees: Float,
    rotationCenter: Offset,
    textMeasurer: TextMeasurer
) {
    val rotatedTextSize = measureRotatedTextSize(
        text = text,
        style = style,
        angleDegrees = angleDegrees,
        textMeasurer = textMeasurer
    )

    val textWidth = rotatedTextSize.width
    val textHeight = rotatedTextSize.height

    val textOffset = Offset(
        x = rotationCenter.x - (textWidth / 2f),
        y = rotationCenter.y - (textHeight / 2f)
    )

    withTransform({
        rotate(
            degrees = angleDegrees,
            pivot = rotationCenter
        )
    }) {
        drawText(
            textMeasurer = textMeasurer,
            text = text,
            topLeft = textOffset,
            style = style
        )
    }
}

/**
 * Creates a Path for the minute hand overlay, which is a visual indicator
 * extending from the outer edge of the clock face. This overlay visually
 * separates the minute hand from the outer seconds dial.
 *
 * The overlay is shaped as a rectangle with rounded ends, forming a capsule-like
 * shape that connects to the minute hand. The width and position of the overlay
 * are dynamically calculated based on the clock's dimensions, the style of the clock,
 * and the space needed for the labels of the second and minutes dials.
 *
 * @param center The center point of the clock face.
 * @param outerRadius The radius of the outer edge of the clock face.
 * @param textMeasurer Used to measure the width of the labels on the clock face,
 *                     which helps in determining the overlay's position.
 * @param clockStyle The style configurations for the clock, including the
 *                   styling for the seconds and minutes dials.
 * @param size The overall size of the drawing area.
 * @return A [Path] object representing the minute hand overlay.
 */
fun DrawScope.createMinuteHandOverlayPath(
    center: Offset,
    outerRadius: Float,
    textMeasurer: TextMeasurer,
    clockStyle: ClockStyle,
    size: Size
): Path {
    return Path()
        .apply {
            val startOffset = Offset(
                x = center.x + (outerRadius * cos(8f * Math.PI / 180f)).toFloat(),
                y = center.y - (outerRadius * sin(8f * Math.PI / 180f)).toFloat(),
            )
            val endOffset = Offset(
                x = center.x + (outerRadius * cos(-8f * Math.PI / 180f)).toFloat(),
                y = center.y - (outerRadius * sin(-8f * Math.PI / 180f)).toFloat(),
            )

            val overlayRadius = (endOffset.y - startOffset.y) / 2f

            val secondsLabelMaxWidth = textMeasurer.measure(
                text = buildAnnotatedString {
                    append("60")
                },
                style = clockStyle
                    .secondsDialStyle
                    .stepsTextStyle
            ).size.width

            val minutesLabelMaxWidth = textMeasurer.measure(
                text = buildAnnotatedString { append("60") },
                style = clockStyle
                    .minutesDialStyle
                    .stepsTextStyle
            ).size.width

            val overlayLineX =
                size.width - clockStyle.secondsDialStyle.fiveStepsLineHeight.toPx() -
                        clockStyle.secondsDialStyle.stepsLabelTopPadding.toPx() -
                        secondsLabelMaxWidth -
                        clockStyle.minutesDialStyle.fiveStepsLineHeight.toPx() -
                        clockStyle.minutesDialStyle.stepsLabelTopPadding.toPx() -
                        (minutesLabelMaxWidth / 2f)

            moveTo(
                x = startOffset.x,
                y = startOffset.y
            )

            lineTo(
                x = overlayLineX,
                y = startOffset.y
            )

            cubicTo(
                x1 = overlayLineX - overlayRadius,
                y1 = startOffset.y,
                x2 = overlayLineX - overlayRadius,
                y2 = endOffset.y,
                x3 = overlayLineX,
                y3 = endOffset.y
            )
            lineTo(endOffset.x, endOffset.y)
        }
}
