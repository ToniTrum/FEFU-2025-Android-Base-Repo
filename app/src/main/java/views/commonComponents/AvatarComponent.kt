package views.commonComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AvatarComponent(word: String, imageUrl: String? = null) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .clip(RoundedCornerShape(percent = 20))
            .background(Color.Gray),
        contentAlignment = Alignment.Center
    ) {
//        if (imageUrl != null) {
//            AsyncImage(
//                model = ImageRequest.Builder(LocalContext.current)
//                    .data(imageUrl)
//                    .crossfade(true)
//                    .build(),
//                contentDescription = "Repository Avatar",
//                modifier = Modifier.fillMaxSize(),
//                contentScale = ContentScale.Crop
//            )
//        } else {
//            Text(
//                text = word.firstOrNull()?.uppercase() ?: "?",
//                fontSize = 30.sp,
//                fontWeight = FontWeight(350),
//                color = Color.White
//            )
//        }
        Text(
            text = word.firstOrNull()?.uppercase() ?: "?",
            fontSize = 30.sp,
            fontWeight = FontWeight(350),
            color = Color.White
        )
    }
}
