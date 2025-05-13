package co.feip.fefu2025.presentation.screens.my_stars_screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import co.feip.fefu2025.R
import co.feip.fefu2025.presentation.common_components.PaginationController
import co.feip.fefu2025.presentation.common_components.RepositoryCard
import co.feip.fefu2025.presentation.common_components.StateManager
import co.feip.fefu2025.presentation.fragments.search_bar_fragment.SearchBarFragment
import co.feip.fefu2025.presentation.fragments.search_bar_fragment.SearchBarViewModel
import co.feip.fefu2025.presentation.navigation.Destination
import co.feip.fefu2025.presentation.navigation.Navigator
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce

@OptIn(FlowPreview::class)
@Composable
fun MyStarsScreen(
    modifier: Modifier = Modifier,
    viewModel: MyStarsViewModel = hiltViewModel(),
    searchBarViewModel: SearchBarViewModel = hiltViewModel(),
    navigator: Navigator
) {
    val state by viewModel.state.collectAsState()
    val searchQuery by searchBarViewModel.searchQuery.collectAsState()

    LaunchedEffect(searchQuery) {
        snapshotFlow { searchQuery }
            .debounce(400)
            .collect { query ->
                viewModel.changePage(1)
                viewModel.getMyStars(search = query)
            }
    }

    LaunchedEffect(state.currentPage) {
        viewModel.getMyStars()
    }

    LaunchedEffect(Unit) {
        viewModel.navigationEvent.collect { destination ->
            when(destination) {
                is Destination.NavigateUp -> navigator.navigateUp()
                is Destination.GitRepositoryDetailScreen ->
                    navigator.navigate(Destination.GitRepositoryDetailScreen(destination.id))
                else -> {}
            }
        }
    }

    LazyColumn(
        modifier = modifier
    ) {
        item {
            SearchBarFragment(
                modifier = Modifier.fillMaxWidth(),
                viewModel = searchBarViewModel
            )
        }

        item {
            Button(
                onClick = {
                    viewModel.navigateToBack()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Black
                )
            ) {
                Text(
                    text = stringResource(R.string.back),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

        item {
            Text(
                text = stringResource(R.string.my_stars),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(15.dp)
            )
        }

        item {
            StateManager(
                state = state,
                onClick = {
                    viewModel.getMyStars()
                }
            ) { }
        }

        if (!state.isLoading && state.error.isEmpty()) {
            if (state.gitRepositoryList.isEmpty()) {
                item {
                    Text(
                        text = stringResource(R.string.repository_not_found),
                        modifier = Modifier.padding(15.dp)
                    )
                }
            } else {
                items(state.gitRepositoryList) { gitRepository ->
                    RepositoryCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        gitRepository = gitRepository,
                        onClick = {
                            viewModel.navigateToGitRepositoryDetailScreen(gitRepository.id)
                        }
                    )
                }

                item {
                    PaginationController(
                        currentPage = state.currentPage,
                        hasNextPage = state.hasNextPage,
                        onPageChange = { newPage ->
                            viewModel.changePage(newPage)
                        }
                    )
                }
            }
        }
    }
}