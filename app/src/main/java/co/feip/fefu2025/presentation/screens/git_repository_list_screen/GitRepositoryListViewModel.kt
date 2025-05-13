package co.feip.fefu2025.presentation.screens.git_repository_list_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.common.Resource
import co.feip.fefu2025.domain.usecase.get_git_repository_list.GetGitRepositoryListUseCase
import co.feip.fefu2025.presentation.navigation.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GitRepositoryListViewModel @Inject constructor(
    private val getGitRepositoryListUseCase: GetGitRepositoryListUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(GitRepositoryListState())
    val state: StateFlow<GitRepositoryListState> = _state

    private val _navigationEvent = MutableSharedFlow<Destination>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    init {
        getGitRepositoryList()
        getMyStars()
    }

    private fun getGitRepositoryList(
        perPage: Int = 10,
        page: Int = 1,
        starred: Boolean = false,
        search: String = ""
    ) {
        getGitRepositoryListUseCase(
            perPage = perPage,
            page = page,
            starred = starred,
            search = search
        ).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        gitRepositoryList = result.data ?: emptyList(),
                        error = "",
                        isLoading = false
                    )
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

    private fun getMyStars() {
        getGitRepositoryListUseCase(
            perPage = 10,
            page = 1,
            starred = true
        ).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        myStars = result.data ?: emptyList(),
                        error = "",
                        isLoading = false
                    )
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

    fun reloadData(
        perPage: Int = 10,
        page: Int = 1,
        starred: Boolean = false,
        search: String = ""
    ) {
        getGitRepositoryList(
            perPage = perPage,
            page = page,
            starred = starred,
            search = search
        )
        getMyStars()
    }

    fun navigateToGitRepositoryDetailScreen(id: Int) {
        viewModelScope.launch {
            _navigationEvent.emit(Destination.GitRepositoryDetailScreen(id))
        }
    }

    fun navigateToMyStarsScreen() {
        viewModelScope.launch {
            _navigationEvent.emit(Destination.MyStarsScreen)
        }
    }
}