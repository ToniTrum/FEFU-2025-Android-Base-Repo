package co.feip.fefu2025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import co.feip.fefu2025.ui.theme.FEFU2025AndroidBaseRepoTheme
import models.Repository
import views.RepositoryCard
import java.util.Date

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val repository = Repository(
            _name = "Name",
            description = "Description",
            _avatar = "https://picsum.photos/200",
            _languages = listOf(
                Pair("Kotlin", 90.134f),
                Pair("XML", 8.435f),
                Pair("Java", 1.431f)
            ),
            _createdAt = Date()
        )
        setContent {
            FEFU2025AndroidBaseRepoTheme {

            }
        }
    }
}