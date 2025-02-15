package sco.carlukesoftware.clockface.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sco.carlukesoftware.clockface.ui.theme.Marron2
import sco.carlukesoftware.clockface.ui.theme.MerriWeather
import sco.carlukesoftware.clockface.ui.theme.WorkSans

/**
 * Object containing predefined styles for different clock appearances.
 *
 * This object provides a collection of [ClockStyle] configurations, each representing
 * a distinct visual theme for the clock. These styles define the colors, text styles,
 * and other visual attributes of the clock's components, such as the hour labels,
 * minute/second steps, and overlay.
 */
object ClockStyles {

    private val defaultStepsTextStyle = TextStyle(
        fontWeight = FontWeight.Medium,
    )

    private val defaultHourLabelStyle = TextStyle(
        fontSize = 60.sp,
        fontWeight = FontWeight.Bold,
    )

    private val defaultOverlayStrokeWidth = 2.dp
    private val defaultStepsLabelTopPadding = 20.dp

    /**
     * Represents a standard clock style configuration.
     *
     * This style defines the appearance of the clock with white elements, including:
     * - **Seconds Dial:** White steps with a slightly transparent white text (Work Sans font, 20sp).
     * - **Minutes Dial:**  Slightly transparent white steps with text (Work Sans font, 18sp).
     * - **Hour Label:** Solid white text (Work Sans font).
     * - **Overlay:** White stroke.
     *
     * It serves as a pre-defined, commonly used style for the clock.
     */
    val StandardClockStyle = ClockStyle(
        secondsDialStyle = DialStyle(
            stepsTextStyle = defaultStepsTextStyle.copy(
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = WorkSans
            ),
            stepsColor = Color.White.copy(alpha = 0.8f),
            stepsLabelTopPadding = defaultStepsLabelTopPadding
        ),
        minutesDialStyle = DialStyle(
            stepsTextStyle = defaultStepsTextStyle.copy(
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 18.sp,
                fontFamily = WorkSans
            ),
            stepsColor = Color.White.copy(alpha = 0.8f),
            stepsLabelTopPadding = defaultStepsLabelTopPadding
        ),
        hourLabelStyle = defaultHourLabelStyle.copy(
            color = Color.White,
            fontFamily = WorkSans
        ),
        overlayStrokeColor = Color.White,
        overlayStrokeWidth = defaultOverlayStrokeWidth
    )

    /**
     * Defines a clock style with a marron color scheme.
     *
     * This style features:
     * - **Seconds Dial:** White text (size 20sp, WorkSans font) on a Marron2 background, with default padding.
     * - **Minutes Dial:** Slightly translucent white text (size 18sp, WorkSans font) on a Marron2 background, with default padding.
     * - **Hour Labels:** White text using the WorkSans font.
     * - **Overlay:** A white stroke with the default width.
     *
     * This style is designed to provide a visually appealing clock face with a distinct color palette.
     */
    val MarronStyle = ClockStyle(
        secondsDialStyle = DialStyle(
            stepsTextStyle = defaultStepsTextStyle.copy(
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = WorkSans
            ),
            stepsColor = Marron2,
            stepsLabelTopPadding = defaultStepsLabelTopPadding
        ),
        minutesDialStyle = DialStyle(
            stepsTextStyle = defaultStepsTextStyle.copy(
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 18.sp,
                fontFamily = WorkSans
            ),
            stepsColor = Marron2,
            stepsLabelTopPadding = defaultStepsLabelTopPadding
        ),
        hourLabelStyle = defaultHourLabelStyle.copy(
            color = Color.White,
            fontFamily = WorkSans
        ),
        overlayStrokeColor = Color.White,
        overlayStrokeWidth = defaultOverlayStrokeWidth
    )

    /**
     * Represents a light style for the clock face.
     *
     * This style is characterized by:
     * - **Black text and elements:** The text for seconds, minutes, and hours is predominantly black,
     *   providing a clear contrast against lighter backgrounds.
     * - **Subtle steps:** The steps for seconds and minutes are rendered in a semi-transparent black,
     *   ensuring they are visible but not overly dominant.
     * - **Prominent hours:** The hour labels are displayed in solid black, making them easily readable.
     * - **Black overlay:** The clock overlay (e.g., the outer ring) is also black.
     * - **WorkSans font:** All text elements use the WorkSans font family for a clean and modern look.
     *
     * This style is suitable for light-themed applications or when a high-contrast
     * clock face is desired.
     */
    val LightStyle = ClockStyle(
        secondsDialStyle = DialStyle(
            stepsTextStyle = defaultStepsTextStyle.copy(
                color = Color.Black.copy(alpha = 0.8f),
                fontSize = 20.sp,
                fontFamily = WorkSans
            ),
            stepsColor = Color.Black.copy(alpha = 0.5f),
            stepsLabelTopPadding = defaultStepsLabelTopPadding
        ),
        minutesDialStyle = DialStyle(
            stepsTextStyle = defaultStepsTextStyle.copy(
                color = Color.Black.copy(alpha = 0.8f),
                fontSize = 18.sp,
                fontFamily = WorkSans
            ),
            stepsColor = Color.Black.copy(alpha = 0.5f),
            stepsLabelTopPadding = defaultStepsLabelTopPadding
        ),
        hourLabelStyle = defaultHourLabelStyle.copy(
            color = Color.Black,
            fontFamily = WorkSans
        ),
        overlayStrokeColor = Color.Black,
        overlayStrokeWidth = defaultOverlayStrokeWidth
    )

    /**
     *  A [ClockStyle] configuration that provides a "brown" themed appearance for the clock.
     *  This style utilizes black colors with varying opacities to create a subtle contrast
     *  against potentially lighter backgrounds. It uses the Merriweather font family for
     *  text elements.
     *
     *  The following aspects are customized in this style:
     *  - **Seconds Dial:**
     *      - `stepsTextStyle`: Black text with 80% opacity, 20sp font size, and Merriweather font.
     *      - `stepsColor`: Black color with 50% opacity.
     *      - `stepsLabelTopPadding`: Uses the default padding.
     *  - **Minutes Dial:**
     *      - `stepsTextStyle`: Black text with 80% opacity, 18sp font size, and Merriweather font.
     *      - `stepsColor`: Black color with 50% opacity.
     *      - `stepsLabelTopPadding`: Uses the default padding.
     *  - **Hour Labels:**
     *      - `hourLabelStyle`: Solid black text, Merriweather font.
     *  - **Overlay Stroke:**
     *      - `overlayStrokeColor`: Solid black color.
     */
    val BrownStyle = ClockStyle(
        secondsDialStyle = DialStyle(
            stepsTextStyle = defaultStepsTextStyle.copy(
                color = Color.Black.copy(alpha = 0.8f),
                fontSize = 20.sp,
                fontFamily = MerriWeather
            ),
            stepsColor = Color.Black.copy(alpha = 0.5f),
            stepsLabelTopPadding = defaultStepsLabelTopPadding
        ),
        minutesDialStyle = DialStyle(
            stepsTextStyle = defaultStepsTextStyle.copy(
                color = Color.Black.copy(alpha = 0.8f),
                fontSize = 18.sp,
                fontFamily = MerriWeather
            ),
            stepsColor = Color.Black.copy(alpha = 0.5f),
            stepsLabelTopPadding = defaultStepsLabelTopPadding
        ),
        hourLabelStyle = defaultHourLabelStyle.copy(
            color = Color.Black,
            fontFamily = MerriWeather
        ),
        overlayStrokeColor = Color.Black,
        overlayStrokeWidth = defaultOverlayStrokeWidth
    )

}
