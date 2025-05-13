package co.feip.fefu2025.presentation.screens.git_repository_detail_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import co.feip.fefu2025.R
import co.feip.fefu2025.presentation.common_components.AvatarIcon
import co.feip.fefu2025.presentation.common_components.CounterWithIcon
import co.feip.fefu2025.presentation.common_components.StateManager
import co.feip.fefu2025.presentation.screens.git_repository_detail_screen.components.LanguageBar
import co.feip.fefu2025.presentation.screens.git_repository_detail_screen.components.UsedLanguagesList
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
        state = state,
        onClick = {
            viewModel.reloadData()
        }
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
                modifier = Modifier.wrapContentHeight()
            ) {
                val symbol: String = gitRepository.name.firstOrNull()?.uppercase() ?: "?"
                AvatarIcon(
                    modifier = Modifier.size(60.dp),
                    symbol = symbol,
                    avatarUrl = gitRepository.avatar
                )
                Text(
                    text = gitRepository.name,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 48.sp
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
                    val languages = (gitRepository.languages).map{ language ->
                        Pair(
                            language.name,
                            round(language.percent * 10) / 10
                        )
                    }

                    Text(
                        text = stringResource(R.string.language_used),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    LanguageBar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                            .height(10.dp),
                        languages = gitRepository.languages
                    )
                    UsedLanguagesList(
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
                    text = gitRepository.createdAt,
                    fontSize = 20.sp
                )
            }
        }
    }
}