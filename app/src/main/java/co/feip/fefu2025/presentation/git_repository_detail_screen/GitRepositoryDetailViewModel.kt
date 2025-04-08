package co.feip.fefu2025.presentation.git_repository_detail_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.common.Resource
import co.feip.fefu2025.domain.usecase.get_git_repository_detail.GetGitRepositoryDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GitRepositoryDetailViewModel @Inject constructor(
    private val getGitRepositoryDetailUseCase: GetGitRepositoryDetailUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(GitRepositoryDetailState())
    val state: StateFlow<GitRepositoryDetailState> = _state

    init {
        savedStateHandle.get<Int>("gitRepositoryId")?.let { gitRepositoryId ->
            getGitRepository(gitRepositoryId)
        }
    }

    private fun getGitRepository(gitRepositoryId: Int) {
        getGitRepositoryDetailUseCase(gitRepositoryId).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = GitRepositoryDetailState(gitRepository = result.data)
                }
                is Resource.Error -> {
                    _state.value = GitRepositoryDetailState(error = result.message ?: "Unexpected error in GitRepositoryListViewModel")
                }
                is Resource.Loading -> {
                    _state.value = GitRepositoryDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}