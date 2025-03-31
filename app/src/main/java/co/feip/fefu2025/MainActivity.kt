package co.feip.fefu2025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import co.feip.fefu2025.ui.theme.FEFU2025AndroidBaseRepoTheme
import models.Repository
import views.PreviewRepositoryScreen
import views.RepositoryCard
import java.util.Date

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            FEFU2025AndroidBaseRepoTheme {
                PreviewRepositoryScreen()
            }
        }
    }
}