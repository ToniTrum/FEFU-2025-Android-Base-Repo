package co.feip.fefu2025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import views.LanguageInformationView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val languageInformationView: LanguageInformationView = findViewById(R.id.viewLI)
        languageInformationView.setText("Kotlin", 100f)
        languageInformationView.setDotColor(0xFFFF0000.toInt())
    }
}