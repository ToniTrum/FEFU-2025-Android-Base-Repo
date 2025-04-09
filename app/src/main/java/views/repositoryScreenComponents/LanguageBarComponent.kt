package views.repositoryScreenComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.feip.fefu2025.ui.theme.languageMap

@Composable
fun LanguageBarComponent(languages: List<Pair<String, Float>>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .height(10.dp)
            .clip(RoundedCornerShape(50))
    ) {
        languages.forEach { (language, percent) ->
            Box(
                modifier = Modifier
                    .weight(percent)
                    .background(color = Color(languageMap[language] ?: Color.Gray.value.toInt()))
                    .fillMaxHeight()
            )
        }
    }
}