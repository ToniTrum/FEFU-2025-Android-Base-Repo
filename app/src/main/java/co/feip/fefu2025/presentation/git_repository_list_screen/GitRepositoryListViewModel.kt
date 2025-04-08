package co.feip.fefu2025.presentation.git_repository_list_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.common.Resource
import co.feip.fefu2025.domain.usecase.get_git_repositories.GetGitRepositoryListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GitRepositoryListViewModel @Inject constructor(
    private val getGitRepositoryListUseCase: GetGitRepositoryListUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(GitRepositoryListState())
    val state: StateFlow<GitRepositoryListState> = _state

    init {
        getGitRepositoryList()
    }

    private fun getGitRepositoryList() {
        getGitRepositoryListUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = GitRepositoryListState(gitRepositoryList = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = GitRepositoryListState(error = result.message ?: "Unexpected error in GitRepositoryListViewModel")
                }
                is Resource.Loading -> {
                    _state.value = GitRepositoryListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}