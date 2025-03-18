package co.feip.fefu2025

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import co.feip.fefu2025.ui.theme.languageMap
import views.CustomLayout
import views.LanguageInformationView
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val customLayout: CustomLayout = findViewById(R.id.vgCustomLayout)
        val btnAdd: Button = findViewById(R.id.btnAdd)

        btnAdd.setOnClickListener {
            val randomLanguage: String = languageMap.keys.random()
            val randomPercent: Float = ((Random.nextDouble(0.0, 100.0) * 10).toInt() / 10.0).toFloat()

            val newLanguageInformationView = LanguageInformationView(this)
            newLanguageInformationView.setText(randomLanguage, randomPercent)
            newLanguageInformationView.setDotColor(languageMap[randomLanguage] ?: 0xFF000000.toInt())

            customLayout.addView(newLanguageInformationView)
            customLayout.requestLayout()
        }
    }
}