package co.feip.fefu2025.presentation.screens.git_repository_list_screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import co.feip.fefu2025.R
import co.feip.fefu2025.presentation.common_components.RepositoryCard
import co.feip.fefu2025.presentation.components.search_bar_component.SearchBarComponent
import co.feip.fefu2025.presentation.common_components.StateManager
import co.feip.fefu2025.presentation.components.search_bar_component.SearchBarViewModel
import co.feip.fefu2025.presentation.navigation.Navigator

@Composable
fun GitRepositoryListScreen(
    modifier: Modifier = Modifier,
    viewModel: GitRepositoryListViewModel = hiltViewModel(),
    searchBarViewModel: SearchBarViewModel = hiltViewModel(),
    navigator: Navigator
) {
    val state by viewModel.state.collectAsState()
    val searchBarState by searchBarViewModel.state.collectAsState()
    val searchQuery by searchBarViewModel.searchQuery.collectAsState()

    val displayedRepositories = searchBarState.gitRepositoryList

    LaunchedEffect(Unit) {
        viewModel.navigationEvent.collect { destination ->
            navigator.navigate(destination)
        }
    }

    StateManager(
        state = state,
        onClick = {
            viewModel.reloadData()
        }
    ) {
        LazyColumn (
            modifier = modifier
        ) {
            item {
                SearchBarComponent(
                    modifier = Modifier.fillMaxWidth(),
                    viewModel = searchBarViewModel
                )
            }

            if (searchQuery.isEmpty()) {
                item {
                    Button(
                        onClick = {
                            viewModel.navigateToMyStarsScreen()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.Black
                        )
                    ) {
                        Text(
                            text = stringResource(R.string.my_stars_with_arrow),
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                }

                item {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(displayedRepositories.take(10)) { gitRepository ->
                            RepositoryCard(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                gitRepository = gitRepository,
                                onClick = {
                                    viewModel.navigateToGitRepositoryDetailScreen(gitRepository.id)
                                })
                        }
                    }
                }

                item {
                    Text(
                        text = stringResource(R.string.all_projects),
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(15.dp)
                    )
                }
            }

            items(displayedRepositories) { gitRepository ->
                RepositoryCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    gitRepository =  gitRepository,
                    onClick = {
                        viewModel.navigateToGitRepositoryDetailScreen(gitRepository.id)
                    }
                )
            }
        }
    }
}