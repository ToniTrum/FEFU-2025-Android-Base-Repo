package co.feip.fefu2025.presentation.git_repository_list_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import co.feip.fefu2025.presentation.git_repository_list_screen.components.RepositoryCardComponent
import co.feip.fefu2025.presentation.navigation.Navigator

@Composable
fun GitRepositoryListScreen(
    viewModel: GitRepositoryListViewModel = hiltViewModel(),
    navigator: Navigator
) {
    val state by viewModel.state.collectAsState()

    if (state.isLoading) {
        CircularProgressIndicator()
        return
    }

    if (state.error.isNotEmpty()) {
        Text(text = "Error: ${state.error}")
        return
    }

    val gitRepositoryList = state.gitRepositoryList
    if (gitRepositoryList.isEmpty()) {
        Text(text = "Repository not found")
        return
    }

    LaunchedEffect(Unit) {
        viewModel.navigationEvent.collect { destination ->
            navigator.navigate(destination)
        }
    }

    LazyColumn (
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Text(
                text = "My Stars",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(15.dp)
            )
        }

        item {
            LazyRow(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(gitRepositoryList.take(10)) { gitRepository ->
                    RepositoryCardComponent(
                        gitRepository = gitRepository,
                        onClick = {
                            viewModel.navigateToGitRepositoryDetailScreen(gitRepository.id)
                        })
                }
            }
        }

        item {
            Text(
                text = "All Projects",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(15.dp)
            )
        }

        items(gitRepositoryList) { gitRepository ->
            RepositoryCardComponent(
                gitRepository =  gitRepository,
                onClick = {
                    viewModel.navigateToGitRepositoryDetailScreen(gitRepository.id)
                })
        }
    }
}