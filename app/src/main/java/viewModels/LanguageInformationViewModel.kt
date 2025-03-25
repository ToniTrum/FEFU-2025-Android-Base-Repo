package viewModels

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import android.view.View
import co.feip.fefu2025.R

class LanguageInformationViewModel @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
): LinearLayout(context, attrs, defStyleAttr) {

    private var tvLanguage: TextView
    private var tvPercent: TextView
    private var viewDot: View

    init {
        LayoutInflater.from(context).inflate(R.layout.view_language_information, this, true)
        tvLanguage = findViewById(R.id.tvLanguage)
        tvPercent = findViewById(R.id.tvPercent)
        viewDot = findViewById(R.id.viewDot)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.LanguageInformationView)
            val language = typedArray.getString(R.styleable.LanguageInformationView_language) ?: "Language"
            val percent = typedArray.getFloat(R.styleable.LanguageInformationView_percent, 0f)
            val dotColor = typedArray.getColor(R.styleable.LanguageInformationView_dotColor, 0xFF000000.toInt())

            setText(language, percent)
            setDotColor(dotColor)

            typedArray.recycle()
        }
    }

    fun setText(language: String, percent: Float) {
        tvLanguage.text = language
        tvPercent.text = "$percent %"
    }

    fun setDotColor(color: Int) {
        val drawable = viewDot.background
        if (drawable is GradientDrawable) {
            drawable.setColor(color)
        }
    }
}