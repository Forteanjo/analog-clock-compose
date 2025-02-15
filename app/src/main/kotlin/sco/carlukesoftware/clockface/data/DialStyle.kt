package sco.carlukesoftware.clockface.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Data class representing the styling options for a dial component.
 *
 * This class allows you to customize the appearance of the dial's steps,
 * including their color, width, line heights, label padding, and text style.
 *
 * @property stepsColor The color of the dial steps. Defaults to [Color.Unspecified],
 *                     which means the steps will inherit their color from the parent or
 *                     have a default color applied.
 * @property stepsWidth The width of the dial steps lines. Defaults to 2.dp.
 * @property normalStepsLineHeight The height of the normal (non-highlighted) dial steps lines.
 *                                 Defaults to 10.dp.
 * @property fiveStepsLineHeight The height of every fifth (or another interval specified by logic)
 *                               dial step line, often used for visual emphasis.
 *                               Defaults to 20.dp.
 * @property stepsLabelTopPadding The padding between the top edge of the dial steps and the
 *                                associated labels. Defaults to 0.dp, meaning the labels are
 *                               directly adjacent to the steps lines.
 * @property stepsTextStyle The text style to be applied to the labels associated with the dial
 *                           steps. Defaults to [TextStyle.Default], which uses the default text
 *                           style of the current composition.
 */
data class DialStyle(
    val stepsColor: Color = Color.Unspecified,
    val stepsWidth: Dp = 2.dp,
    val normalStepsLineHeight: Dp = 10.dp,
    val fiveStepsLineHeight: Dp = 20.dp,
    val stepsLabelTopPadding: Dp = 0.dp,
    val stepsTextStyle: TextStyle = TextStyle.Default
)
