package co.feip.fefu2025

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.feip.fefu2025.ui.theme.FEFU2025AndroidBaseRepoTheme
import views.PreviewMyStarsSection
import views.commonComponents.PreviewSearchLineComponent


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            FEFU2025AndroidBaseRepoTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    PreviewSearchLineComponent()
                    PreviewMyStarsSection()
                }
            }

        }
    }
}