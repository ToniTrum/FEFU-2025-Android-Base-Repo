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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import co.feip.fefu2025.R
import co.feip.fefu2025.presentation.components.AvatarComponent
import co.feip.fefu2025.presentation.components.CounterWithIcon
import co.feip.fefu2025.presentation.components.StateManager
import co.feip.fefu2025.presentation.git_repository_detail_screen.components.LanguageBarComponent
import co.feip.fefu2025.presentation.git_repository_detail_screen.components.UsedLanguagesComponent
import co.feip.fefu2025.presentation.navigation.Navigator
import kotlin.math.round

@Composable
fun GitRepositoryDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: GitRepositoryDetailViewModel = hiltViewModel(),
    navigator: Navigator,
    gitRepositoryId: Int
) {
    var shouldNavigateBack by remember { mutableStateOf(false) }
    BackHandler {
        shouldNavigateBack = true
    }
    if (shouldNavigateBack) {
        LaunchedEffect(Unit) {
            navigator.navigateUp()
        }
    }

    LaunchedEffect(gitRepositoryId) {
        viewModel.setGitRepositoryId(gitRepositoryId)
    }

    val state by viewModel.state.collectAsState()

    StateManager(
        state = state
    ) {
        val gitRepository = state.gitRepository
        if (gitRepository == null) {
            Text(
                text = stringResource(R.string.repository_not_found)
            )
            return@StateManager
        }

        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val symbol: String = gitRepository.name.firstOrNull()?.uppercase() ?: "?"
                AvatarComponent(
                    modifier = Modifier.size(60.dp),
                    symbol = symbol,
                    imageUrl = gitRepository.avatar
                )
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

            if (gitRepository.languages.isNotEmpty())
            {
                Column(
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    val languages = (gitRepository.languages)
                        .map{ (language, percent) -> language to round(percent * 10) / 10 }

                    Text(
                        text = stringResource(R.string.language_used),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    LanguageBarComponent(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                            .height(10.dp),
                        languages = gitRepository.languages
                    )
                    UsedLanguagesComponent(
                        languages = languages,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Column {
                Text(
                    text = stringResource(R.string.created_at),
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
}