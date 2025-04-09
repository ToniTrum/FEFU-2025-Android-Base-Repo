package views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CounterWithIcon(icon: Painter, count: Int = 0, text: String? = null) {
    Row(
        modifier = Modifier
            .padding(2.dp)
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = icon,
            contentDescription = text,
            modifier = Modifier.size(16.sp.value.dp)
        )

        Text(
            text = "$count",
            fontSize = 16.sp,
            color = Color.Gray
        )

        if (text != null)
            Text(
                text = text,
                fontSize = 16.sp,
                color = Color.Gray
            )
    }
}