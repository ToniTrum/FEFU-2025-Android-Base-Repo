package co.feip.fefu2025.presentation.git_repository_list_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.feip.fefu2025.R
import co.feip.fefu2025.domain.model.GitRepository
import co.feip.fefu2025.presentation.components.AvatarComponent
import co.feip.fefu2025.presentation.components.CounterWithIcon

@Composable
fun RepositoryCardComponent(
    gitRepository: GitRepository
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFEEEEEE)),
        border = BorderStroke(3.dp, Color.Black)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            AvatarComponent(gitRepository.name, gitRepository.avatar)

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
                        color = Color.Gray
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CounterWithIcon(
                        icon = painterResource(id = R.drawable.ic_star),
                        count = gitRepository.starCount,
                        text = "Stars"
                    )
                    CounterWithIcon(
                        icon = painterResource(id = R.drawable.ic_fork),
                        count = gitRepository.forkCount,
                        text = "Forks"
                    )
                }
            }
        }
    }
}