package co.feip.fefu2025

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge

class MainActivity : ComponentActivity() {
    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val textView: TextView = findViewById(R.id.textViewClick)
        val button: Button = findViewById(R.id.button)

        counter = savedInstanceState?.getInt("counter", 0) ?: 0
        textView.text = "У вас кликов: $counter"

        button.setOnClickListener{
            counter++
            textView.text = "У вас кликов: $counter"
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("counter", counter)
    }
}