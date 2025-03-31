package views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import java.util.Date
import kotlin.math.round

@Composable
fun RepositoryScreen(repository: Repository) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(15.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AvatarComponent(repository.name, repository.avatar)
            Text(
                text = repository.name,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
        }

        if (repository.description != null) {
            Text(
                text = repository.description.toString(),
                fontSize = 20.sp,
                color = Color.Gray
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
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

        if (!repository.languages.isNullOrEmpty())
        {
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                val languages = (repository.languages as List<Pair<String, Float>>)
                    .map{ (language, percent) -> language to round(percent * 10) / 10 }

                Text(
                    text = "Language used",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                LanguageBarComponent(repository.languages!!)
                UsedLanguagesComponent(
                    languages = languages,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Column {
            Text(
                text = "Created at",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = repository.createdAt.toString(),
                fontSize = 20.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRepositoryScreen() {
    val repository = Repository(
        _name = "Name",
        description = "Description",
        _avatar = "https://picsum.photos/200",
        _languages = listOf(
            Pair("Kotlin", 90.134f),
            Pair("XML", 8.435f),
            Pair("Java", 1.431f)
        ),
        _createdAt = Date()
    )
    RepositoryScreen(repository)
}