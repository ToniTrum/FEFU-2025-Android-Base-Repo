package views.commonComponents

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.feip.fefu2025.R
import models.Repository

@Composable
fun RepositoryCardComponent(repository: Repository) {
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
            AvatarComponent(repository.name)

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = repository.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                if (repository.description != null) {
                    Text(
                        text = repository.description.toString(),
                        fontSize = 16.sp,
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
                        count = repository.starCount,
                        text = "Stars"
                    )
                    CounterWithIcon(
                        icon = painterResource(id = R.drawable.ic_fork),
                        count = repository.forkCount,
                        text = "Forks"
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewRepositoryCardComponent() {
    RepositoryCardComponent(
        repository = Repository(
            "Name",
            "Description",
            _avatar = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQvWCuHpb9UaPGPT-gO_pJ1ER-IMJ2WoYO_tQ&s"
        )
    )
}