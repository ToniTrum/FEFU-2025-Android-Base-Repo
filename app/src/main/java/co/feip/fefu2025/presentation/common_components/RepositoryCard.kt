package co.feip.fefu2025.presentation.common_components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.feip.fefu2025.R
import co.feip.fefu2025.domain.model.GitRepositoryDomain

@Composable
fun RepositoryCard(
    modifier: Modifier = Modifier,
    gitRepository: GitRepositoryDomain,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFEEEEEE)),
        border = BorderStroke(3.dp, Color.Black),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            val symbol: String = gitRepository.name.firstOrNull()?.uppercase() ?: "?"
            AvatarIcon(
                modifier = Modifier.size(60.dp),
                symbol = symbol,
                avatarUrl = gitRepository.avatar
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = gitRepository.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                gitRepository.description?.let {
                    Text(
                        text = it,
                        fontSize = 20.sp,
                        color = Color.Gray,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CounterWithIcon(
                        modifier = Modifier.padding(2.dp),
                        icon = painterResource(id = R.drawable.ic_star),
                        text = stringResource(R.string.star_count, gitRepository.starCount)
                    )
                    CounterWithIcon(
                        modifier = Modifier.padding(2.dp),
                        icon = painterResource(id = R.drawable.ic_fork),
                        text = stringResource(R.string.fork_count, gitRepository.forkCount)
                    )
                }
            }
        }
    }
}