package co.feip.fefu2025.presentation.screens.git_repository_detail_screen

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
) : ViewModel() {
    private val _state = MutableStateFlow(GitRepositoryDetailState())
    val state: StateFlow<GitRepositoryDetailState> = _state

    private val _gitRepositoryId = MutableStateFlow<Int?>(null)

    init {
        _gitRepositoryId.value?.let {
            getGitRepositoryDetail(it)
        }
    }

    private fun getGitRepositoryDetail(gitRepositoryId: Int) {
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

    fun reloadData() {
        _gitRepositoryId.value?.let {
            getGitRepositoryDetail(it)
        }
    }

    fun setGitRepositoryId(gitRepositoryId: Int) {
        if (_gitRepositoryId.value != gitRepositoryId) {
            _gitRepositoryId.value = gitRepositoryId
            getGitRepositoryDetail(gitRepositoryId)
        }
    }
}