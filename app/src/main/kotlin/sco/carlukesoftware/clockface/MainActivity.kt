package sco.carlukesoftware.clockface

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import sco.carlukesoftware.clockface.data.ClockStyles.StandardClockStyle
import sco.carlukesoftware.clockface.ui.ClockFace
import sco.carlukesoftware.clockface.ui.theme.ClockFaceTheme

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClockFaceTheme(darkTheme = true) {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        verticalArrangement = Arrangement
                            .Center,
                        horizontalAlignment = Alignment
                            .CenterHorizontally,
                    ) {
                        ClockFace(
                            clockStyle = StandardClockStyle
                        )
                    }
                }
            }
        }
    }
}
