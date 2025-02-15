package sco.carlukesoftware.clockface.utils

import androidx.compose.ui.geometry.Offset
import kotlin.math.cos
import kotlin.math.sin

/**
 * Calculates the offset of a point on a circle's circumference relative to the circle's center.
 *
 * This function determines the coordinates of a point lying on the edge of a circle, given the
 * circle's center, its radius, and the angle (in radians) of the point relative to the horizontal axis.
 * It uses basic trigonometry (cosine and sine) to determine the x and y displacements from the center.
 *
 * Note: The y-coordinate is calculated as `center.y - (radius * sin(angleInRadians))`.
 * This is because in Compose's coordinate system, the y-axis increases downwards. Therefore,
 * to move "up" the y-axis (towards smaller y values), we subtract from `center.y`.
 *
 * @param center The [Offset] representing the center point of the circle.
 * @param radius The radius of the circle.
 * @param angleInRadians The angle, in radians, of the point on the circle relative to the horizontal axis (0 radians is to the right).
 * @return An [Offset] representing the coordinates of the point on the circle's circumference.
 */
fun calculateOffset(
    center: Offset,
    radius: Float,
    angleInRadians: Double
): Offset = Offset(
    x = center.x + (radius * cos(angleInRadians)).toFloat(),
    y = center.y - (radius * sin(angleInRadians)).toFloat()
)
