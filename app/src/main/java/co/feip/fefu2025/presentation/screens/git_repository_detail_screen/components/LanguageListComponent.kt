package co.feip.fefu2025.presentation.screens.git_repository_detail_screen.components

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import android.view.View
import co.feip.fefu2025.R
import androidx.core.content.withStyledAttributes

class LanguageListComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): LinearLayout(context, attrs, defStyleAttr) {

    private var tvLanguage: TextView
    private var tvPercent: TextView
    private var viewDot: View

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_language_information, this, true)
        tvLanguage = findViewById(R.id.tvLanguage)
        tvPercent = findViewById(R.id.tvPercent)
        viewDot = findViewById(R.id.viewDot)

        attrs?.let {
            context.withStyledAttributes(it, R.styleable.LanguageInformationView) {
                val language = Pair(
                    getString(R.styleable.LanguageInformationView_language) ?: "Language",
                    getFloat(R.styleable.LanguageInformationView_percent, 0f)
                )
                val dotColor =
                    getColor(R.styleable.LanguageInformationView_dotColor, 0xFF000000.toInt())

                setText(language)
                setDotColor(dotColor)

            }
        }
    }

    fun setText(language: Pair<String, Float>) {
        tvLanguage.text = language.first
        tvPercent.text = "${language.second} %"
    }

    fun setDotColor(color: Int) {
        val drawable = viewDot.background
        if (drawable is GradientDrawable) {
            drawable.setColor(color)
        }
    }
}