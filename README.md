# Analog Clock with Customizable Styles in Jetpack Compose

[![License](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.0.0-blue.svg)](https://kotlinlang.org/)
[![Compose](https://img.shields.io/badge/Compose-1.5.4-blue.svg)](https://developer.android.com/jetpack/compose)
[![API](https://img.shields.io/badge/API-26%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=26)

This project demonstrates how to create a fully customizable analog clock using Jetpack Compose, the modern UI toolkit for Android. It showcases advanced drawing techniques with `Canvas`, real-time time updates, text rotation, and multiple clock styles.

## Features

-   **Analog Clock:** A visually appealing analog clock face with hour, minute, and second hands.
-   **Customizable Styles:** Multiple built-in clock styles (Standard, Marron, Light, Brown) and the ability to easily create new ones.
-   **Real-time Updates:** The clock hands move smoothly in real-time, updating every second.
-   **Custom Drawing:** Utilizes Jetpack Compose's `Canvas` to draw the clock face, hands, and minute/second dials.
-   **Text Rotation:** Demonstrates how to rotate text at any angle using `Canvas` transformations.
-   **Rotated Text Measurement:** Accurately measures the size of rotated text to ensure proper layout.
-   **Clean Code:** Follows Kotlin and Jetpack Compose best practices for readability and maintainability.
-   **Modern Time API:** Uses the modern `java.time` API for handling time.

## Screenshots

<!-- Add screenshots or GIFs here -->

![Screenshot 1](screenshots/screenshot1.png)
![Screenshot 2](screenshots/screenshot2.png)
![Screenshot 3](screenshots/screenshot3.png)
![Screenshot 4](screenshots/screenshot4.png)

## Getting Started

### Prerequisites

-   Android Studio (latest version recommended)
-   Android SDK (API level 26 or higher)
-   Basic knowledge of Kotlin and Jetpack Compose

### Installation

1.  **Clone the repository:**
2.  **Open in Android Studio:**
    -   Open Android Studio and select "Open an existing Android Studio project."
    -   Navigate to the cloned repository directory and select it.

3.  **Build and Run:**
    -   Connect an Android device or start an emulator.
    -   Click the "Run" button in Android Studio.

## Usage

The main composable function is `ClockFace()`. You can include it in your Compose UI like this:

kotlin 
import androidx.compose.foundation.layout.fillMaxSize 
import androidx.compose.runtime.Composable 
import androidx.compose.ui.Modifier 
import com.yourpackage.ClockFace 
import com.yourpackage.ClockStyles.StandardClockStyle

@Composable 
fun MyScreen() { 
    ClockFace(modifier = Modifier.fillMaxSize( ,  
    clockStyle = StandardClockStyle) 
}

You can easily change the clock style by passing a different `ClockStyle` object:

kotlin import com.yourpackage.ClockStyles.MarronStyle
// ... ClockFace( 
            modifier = Modifier.fillMaxSize() ,  
            clockStyle = MarronStyle 
        )

## Code Structure

-   **`ClockFace.kt`:** Contains the main `ClockFace` composable function, which draws the entire clock.
-   **`ClockDial.kt`:** Contains the `clockDial` function, which draws the minute and second dials.
-   **`ClockStyle.kt`:** Defines the `ClockStyle`, `DialStyle` data classes, and the `ClockStyles` object with the predefined styles.
-   **`rememberCurrentTime.kt`:** Contains the `rememberCurrentTime` composable function for getting the current time.
  - **`RotatedText.kt`**: Contains the composable function for drawing rotated text.

## Dependencies

-   **Jetpack Compose:**
    -   `androidx.compose.ui:ui`
    -   `androidx.compose.material:material`
    -   `androidx.compose.ui:ui-tooling-preview`
    -   `androidx.compose.ui:ui-graphics`
-   **Kotlin Coroutines:**
    -   `org.jetbrains.kotlinx:kotlinx-coroutines-android`
-   **Android Core KTX:**
    -   `androidx.core:core-ktx`

## Contributing

Contributions are welcome! If you'd like to contribute to this project, please follow these steps:

1.  Fork the repository.
2.  Create a new branch for your feature or bug fix.
3.  Make your changes and commit them.
4.  Push your changes to your forked repository.
5.  Submit a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

Donald McCaskey - [forteanjo@sky.com](mailto:forteanjo@sky.com)

Project Link: [https://github.com/forteanjo/analog-clock-compose](https://github.com/forteanjo/analog-clock-compose)
