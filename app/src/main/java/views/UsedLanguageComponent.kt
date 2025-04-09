package views

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import co.feip.fefu2025.ui.theme.languageMap
import viewModels.CustomLayout
import viewModels.LanguageInformationViewModel

@Composable
fun UsedLanguagesComponent(
    languages: List<Pair<String, Float>>,
    modifier: Modifier = Modifier
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            CustomLayout(context).apply {
                languages.forEach { (language, percent) ->
                    val languageView = LanguageInformationViewModel(context).apply {
                        setText(language, percent)
                        setDotColor(languageMap[language] ?: 0xFF000000.toInt())
                    }
                    addView(languageView)
                }
            }
        },
        update = { customLayout ->
            customLayout.removeAllViews()
            languages.forEach { (language, percent) ->
                val languageView = LanguageInformationViewModel(customLayout.context).apply {
                    setText(language, percent)
                    setDotColor(languageMap[language] ?: 0xFF000000.toInt())
                }
                customLayout.addView(languageView)
            }
        }
    )
}
