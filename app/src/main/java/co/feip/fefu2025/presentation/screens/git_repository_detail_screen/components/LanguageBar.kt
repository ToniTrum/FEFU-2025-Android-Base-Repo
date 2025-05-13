package co.feip.fefu2025.presentation.screens.git_repository_detail_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import co.feip.fefu2025.common.languageMap
import co.feip.fefu2025.domain.model.LanguageUsedDomain

@Composable
fun LanguageBar(
    modifier: Modifier = Modifier,
    languages: List<LanguageUsedDomain>
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(50))
    ) {
        languages.forEach { (language, percent) ->
            if (percent != 0f)
            {
                Box(
                    modifier = Modifier
                        .weight(percent)
                        .background(
                            color = languageMap[language]?.let {
                                Color(it)
                            } ?: Color.Gray
                        )
                        .fillMaxHeight()
                )
            }
        }
    }
}