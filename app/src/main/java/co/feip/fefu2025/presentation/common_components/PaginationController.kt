package co.feip.fefu2025.presentation.common_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.feip.fefu2025.R

@Composable
fun PaginationController(
    currentPage: Int,
    hasNextPage: Boolean,
    onPageChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(80.dp, 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { onPageChange(currentPage - 1) },
            enabled = currentPage > 1,
            colors = ButtonDefaults.buttonColors(
                disabledContainerColor = androidx.compose.ui.graphics.Color.Gray
            )
        ) {
            Text(
                text = stringResource(R.string.previous),
                fontSize = 16.sp
            )
        }

        Text(
            text = currentPage.toString(),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Button(
            onClick = { onPageChange(currentPage + 1) },
            enabled = !hasNextPage,
            colors = ButtonDefaults.buttonColors(
                disabledContainerColor = androidx.compose.ui.graphics.Color.Gray
            )
        ) {
            Text(
                text = stringResource(R.string.next),
                fontSize = 16.sp
            )
        }
    }
}