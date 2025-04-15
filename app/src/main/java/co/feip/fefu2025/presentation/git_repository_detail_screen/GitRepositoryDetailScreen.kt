package co.feip.fefu2025.presentation.git_repository_detail_screen

import androidx.activity.compose.BackHandler
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import co.feip.fefu2025.R
import co.feip.fefu2025.domain.model.GitRepository
import co.feip.fefu2025.presentation.components.AvatarComponent
import co.feip.fefu2025.presentation.components.CounterWithIcon
import co.feip.fefu2025.presentation.git_repository_detail_screen.components.LanguageBarComponent
import co.feip.fefu2025.presentation.git_repository_detail_screen.components.UsedLanguagesComponent
import co.feip.fefu2025.presentation.navigation.Navigator
import kotlin.math.round

@Composable
fun GitRepositoryDetailScreen(
    viewModel: GitRepositoryDetailViewModel = hiltViewModel(),
    navigator: Navigator,
    gitRepositoryId: Int
) {
    LaunchedEffect(gitRepositoryId) {
        viewModel.setGitRepositoryId(gitRepositoryId)
    }

    val state by viewModel.state.collectAsState()

    if (state.isLoading) {
        CircularProgressIndicator()
        return
    }

    if (state.error.isNotEmpty()) {
        Text(text = "Error: ${state.error}")
        return
    }

    val gitRepository = state.gitRepository
    if (gitRepository == null) {
        Text(text = "Repository not found")
        return
    }

    var shouldNavigateBack by remember { mutableStateOf(false) }
    BackHandler {
        shouldNavigateBack = true
    }

    if (shouldNavigateBack) {
        LaunchedEffect(Unit) {
            navigator.navigateUp()
        }
    }

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
            AvatarComponent(gitRepository.name, gitRepository.avatar)
            Text(
                text = gitRepository.name,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
        }

        gitRepository.description?.let {
            Text(
                text = it,
                fontSize = 20.sp,
                color = Color.Gray
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
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

        if (gitRepository.languages.isNotEmpty())
        {
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                val languages = (gitRepository.languages)
                    .map{ (language, percent) -> language to round(percent * 10) / 10 }

                Text(
                    text = "Language used",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                LanguageBarComponent(gitRepository.languages)
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
                text = gitRepository.createdAt.toString(),
                fontSize = 20.sp
            )
        }
    }
}