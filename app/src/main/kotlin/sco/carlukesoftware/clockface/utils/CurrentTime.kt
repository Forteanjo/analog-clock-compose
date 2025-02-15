package sco.carlukesoftware.clockface.utils

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import java.time.LocalTime

/**
 * Remembers and updates the current time every second.
 *
 * This composable function provides a `LocalTime` that is continuously updated
 * to reflect the current time. It utilizes `remember` to store the time and
 * `LaunchedEffect` to periodically update it. The update occurs every second
 * using a `delay` of 1000 milliseconds.
 *
 * Note: This function requires API level 26 (Android 8.0) or higher due to the use of `LocalTime`.
 *
 * @return A `LocalTime` representing the current time, updated every second.
 *
 * @throws IllegalStateException if called on devices with API level below 26.
 * @sample
 *  ```
 *  @Composable
 *  fun MyTimeDisplay() {
 *      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
 *           val currentTime = rememberCurrentTime()
 *           Text(text = "Current time: ${currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"))}")
 *      } else {
 *          Text(text = "Time display not supported on this device.")
 *      }
 *  }
 *  ```
 */
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun rememberCurrentTime(): LocalTime {
    var currentTime by remember {
        mutableStateOf(
            LocalTime
                .now()
        )
    }

    LaunchedEffect(Unit) {
        while (true) {
            currentTime = LocalTime
                .now()

            delay(1000)
        }
    }

    return currentTime
}
