package sco.carlukesoftware.clockface.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Data class representing the styling options for a clock.
 *
 * This class allows customization of the visual appearance of a clock, including
 * the dials for seconds and minutes, the hour labels, and the overlay stroke.
 *
 * @property secondsDialStyle The styling for the seconds dial. Defaults to a default [DialStyle].
 * @property minutesDialStyle The styling for the minutes dial. Defaults to a default [DialStyle].
 * @property hourLabelStyle The text style for the hour labels. Defaults to a default [TextStyle].
 * @property overlayStrokeWidth The width of the stroke for the clock's overlay (e.g., a circle). Defaults to 2.dp.
 * @property overlayStrokeColor The color of the stroke for the clock's overlay. Defaults to [Color.Red].
 */
data class ClockStyle(
    val secondsDialStyle: DialStyle = DialStyle(),
    val minutesDialStyle: DialStyle = DialStyle(),
    val hourLabelStyle: TextStyle = TextStyle(),
    val overlayStrokeWidth: Dp = 2.dp,
    val overlayStrokeColor: Color = Color.Red
)
