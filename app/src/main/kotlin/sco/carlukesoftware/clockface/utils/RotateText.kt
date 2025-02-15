package sco.carlukesoftware.clockface.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

/**
 * Measures the size of text after it has been rotated by a given angle.
 *
 * This function calculates the bounding box dimensions of a rotated text string.
 * It first measures the size of the text without rotation using [TextMeasurer].
 * Then, it calculates the new bounding box dimensions after the text is rotated
 * by the specified angle.
 *
 * @param text The text string to measure.
 * @param style The [TextStyle] to apply to the text.
 * @param angleDegrees The angle of rotation in degrees. Positive values represent clockwise rotation.
 * @param textMeasurer The [TextMeasurer] to use for measuring the text.
 * @return A [Size] object representing the width and height of the rotated text's bounding box.
 */
fun measureRotatedTextSize(
    text: String,
    style: TextStyle,
    angleDegrees: Float,
    textMeasurer: TextMeasurer
): Size {
    val textLayoutResult = textMeasurer.measure(text = text, style = style)
    val textWidth = textLayoutResult.size.width.toFloat()
    val textHeight = textLayoutResult.size.height.toFloat()

    val angleRadians = Math.toRadians(angleDegrees.toDouble()).toFloat()

    // Calculate the corners of the un-rotated text
    val topLeft = Offset(0f, 0f)
    val topRight = Offset(textWidth, 0f)
    val bottomLeft = Offset(0f, textHeight)
    val bottomRight = Offset(textWidth, textHeight)

    // Rotate the corners
    val rotatedTopLeft = rotatePoint(topLeft, angleRadians)
    val rotatedTopRight = rotatePoint(topRight, angleRadians)
    val rotatedBottomLeft = rotatePoint(bottomLeft, angleRadians)
    val rotatedBottomRight = rotatePoint(bottomRight, angleRadians)

    // Find the min and max x and y values of the rotated corners
    val minX = minOf(rotatedTopLeft.x, rotatedTopRight.x, rotatedBottomLeft.x, rotatedBottomRight.x)
    val maxX = maxOf(rotatedTopLeft.x, rotatedTopRight.x, rotatedBottomLeft.x, rotatedBottomRight.x)
    val minY = minOf(rotatedTopLeft.y, rotatedTopRight.y, rotatedBottomLeft.y, rotatedBottomRight.y)
    val maxY = maxOf(rotatedTopLeft.y, rotatedTopRight.y, rotatedBottomLeft.y, rotatedBottomRight.y)

    // Calculate the width and height of the rotated bounding box
    val rotatedWidth = abs(maxX - minX)
    val rotatedHeight = abs(maxY - minY)

    return Size(rotatedWidth, rotatedHeight)
}

/**
 * Rotates a 2D point around the origin (0, 0) by a specified angle.
 *
 * This function takes an [Offset] representing a 2D point and rotates it counter-clockwise
 * around the origin (0, 0) by the given angle in radians. It uses standard trigonometric
 * rotation formulas to calculate the new coordinates of the rotated point.
 *
 * @param point The [Offset] representing the point to be rotated.
 * @param angleRadians The angle of rotation in radians (counter-clockwise).
 * @return A new [Offset] representing the rotated point.
 *
 * Example Usage:
 * ```
 * val originalPoint = Offset(10f, 0f)
 * val angle = Math.PI.toFloat() / 2f // 90 degrees in radians
 * val rotatedPoint = rotatePoint(originalPoint, angle)
 * // rotatedPoint will be approximately Offset(0f, 10f)
 * ```
 */
fun rotatePoint(point: Offset, angleRadians: Float): Offset {
    val rotatedX = point.x * cos(angleRadians) - point.y * sin(angleRadians)
    val rotatedY = point.x * sin(angleRadians) + point.y * cos(angleRadians)
    return Offset(rotatedX, rotatedY)
}
