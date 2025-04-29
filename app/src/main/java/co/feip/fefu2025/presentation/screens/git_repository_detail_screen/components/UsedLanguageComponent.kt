package co.feip.fefu2025.presentation.screens.git_repository_detail_screen.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import co.feip.fefu2025.common.languageMap

@Composable
fun UsedLanguagesComponent(
    modifier: Modifier = Modifier,
    languages: List<Pair<String, Float>>
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            FlexBoxLayout(context).apply {
                languages.forEach { language ->
                    val languageView = LanguageListComponent(context).apply {
                        setText(language)
                        setDotColor(languageMap[language.first] ?: 0xFF000000.toInt())
                    }
                    addView(languageView)
                }
            }
        },
        update = { customLayout ->
            customLayout.removeAllViews()
            languages.forEach { language ->
                val languageView = LanguageListComponent(customLayout.context).apply {
                    setText(language)
                    setDotColor(languageMap[language.first] ?: 0xFF000000.toInt())
                }
                customLayout.addView(languageView)
            }
        }
    )
}
