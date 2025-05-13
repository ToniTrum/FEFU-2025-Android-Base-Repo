package co.feip.fefu2025.presentation.screens.git_repository_detail_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.common.Resource
import co.feip.fefu2025.domain.usecase.get_git_repository_detail.GetGitRepositoryDetailUseCase
import co.feip.fefu2025.domain.usecase.get_git_repository_list.GetGitRepositoryListUseCase
import co.feip.fefu2025.domain.usecase.star_git_repository.StarGitRepositoryUseCase
import co.feip.fefu2025.domain.usecase.star_git_repository.UnstarGitRepositoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GitRepositoryDetailViewModel @Inject constructor(
    private val getGitRepositoryListUseCase: GetGitRepositoryListUseCase,
    private val getGitRepositoryDetailUseCase: GetGitRepositoryDetailUseCase,
    private val starGitRepositoryUseCase: StarGitRepositoryUseCase,
    private val unstarGitRepositoryUseCase: UnstarGitRepositoryUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(GitRepositoryDetailState())
    val state: StateFlow<GitRepositoryDetailState> = _state

    private val _gitRepositoryId = MutableStateFlow<Int?>(null)

    fun setGitRepositoryId(gitRepositoryId: Int) {
        if (_gitRepositoryId.value != gitRepositoryId) {
            _gitRepositoryId.value = gitRepositoryId
            getGitRepositoryDetail(gitRepositoryId)
            checkStar(gitRepositoryId)
        }
    }

    fun getGitRepositoryDetail(gitRepositoryId: Int) {
        getGitRepositoryDetailUseCase(gitRepositoryId).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        gitRepository = result.data,
                        error = "",
                        isLoading = false
                    )
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        error = result.message ?: "Unexpected error in GitRepositoryListViewModel",
                        isLoading = false
                    )
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun starGitRepository(gitRepositoryId: Int) {
        starGitRepositoryUseCase(gitRepositoryId).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        isStarLoading = false,
                        starError = "",
                        gitRepository = _state.value.gitRepository?.copy(
                            starCount = result.data?.starCount ?: _state.value.gitRepository?.starCount ?: 0
                        ),
                        isStarred = true
                    )
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        starError = result.message ?: "Unexpected error in GitRepositoryListViewModel",
                        isStarLoading = false
                    )
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isStarLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun unstarGitRepository(gitRepositoryId: Int) {
        unstarGitRepositoryUseCase(gitRepositoryId).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        isStarLoading = false,
                        starError = "",
                        gitRepository = _state.value.gitRepository?.copy(
                            starCount = result.data?.starCount ?: _state.value.gitRepository?.starCount ?: 0
                        ),
                        isStarred = false
                    )
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        starError = result.message ?: "Unexpected error in GitRepositoryListViewModel",
                        isStarLoading = false
                    )
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isStarLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun checkStar(gitRepositoryId: Int) {
        getGitRepositoryListUseCase(
            page = 1,
            perPage = 60,
            starred = true,
            search = _state.value.gitRepository?.name ?: ""
        ).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    val gitRepositoryList = result.data?.gitRepositoryList ?: emptyList()
                    val isStarred = gitRepositoryList.any { it.id == gitRepositoryId}
                    _state.value = _state.value.copy(
                        isStarred = isStarred,
                        isStarLoading = false,
                        starError = ""
                    )
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        starError = result.message ?: "Unexpected error in GitRepositoryListViewModel",
                        isStarLoading = false
                    )
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isStarLoading = true
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}