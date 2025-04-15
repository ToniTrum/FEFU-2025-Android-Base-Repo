package co.feip.fefu2025.presentation.my_stars_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import co.feip.fefu2025.presentation.components.RepositoryCardComponent
import co.feip.fefu2025.presentation.navigation.Destination
import co.feip.fefu2025.presentation.navigation.Navigator

@Composable
fun MyStarsScreen(
    viewModel: MyStarsViewModel = hiltViewModel(),
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
            when(destination) {
                is Destination.NavigateUp -> navigator.navigateUp()
                is Destination.GitRepositoryDetailScreen ->
                    navigator.navigate(Destination.GitRepositoryDetailScreen(destination.id))
                else -> {}
            }
        }
    }

    LazyColumn (
        modifier = Modifier.fillMaxSize()
    ) {
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
                    text = "< Back",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }

        item {
            Text(
                text = "My Stars",
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
                }
            )
        }
    }
}