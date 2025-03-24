package co.feip.fefu2025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import co.feip.fefu2025.ui.theme.FEFU2025AndroidBaseRepoTheme
import models.Repository
import views.RepositoryCard

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            FEFU2025AndroidBaseRepoTheme {
                RepositoryCard(
                    repository = Repository(
                        "Name",
                        "Description",
                        _avatar = "https://picsum.photos/200"
                    )
                )
            }
        }
    }
}