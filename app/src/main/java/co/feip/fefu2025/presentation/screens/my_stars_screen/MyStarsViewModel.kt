package co.feip.fefu2025.presentation.screens.my_stars_screen

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
class MyStarsViewModel @Inject constructor(
    private val getMyStarsUseCase: GetGitRepositoryListUseCase
): ViewModel() {
    private val _state = MutableStateFlow(MyStarsState())
    val state: StateFlow<MyStarsState> = _state

    private val _navigationEvent = MutableSharedFlow<Destination>()
    val navigationEvent = _navigationEvent.asSharedFlow()

    init {
        getMyStars()
    }

    fun getMyStars(
        perPage: Int = 10,
        search: String = ""
    ) {
        getMyStarsUseCase(
            perPage = perPage,
            page = state.value.currentPage,
            starred = true,
            search = search
        ).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        gitRepositoryList = result.data?.gitRepositoryList ?: emptyList(),
                        hasNextPage = result.data?.hasNextPage ?: false,
                        isLoading = false,
                        error = ""
                    )
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        error = result.message ?: "Unexpected error in MyStarsViewModel",
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

    fun navigateToGitRepositoryDetailScreen(id: Int) {
        viewModelScope.launch {
            _navigationEvent.emit(Destination.GitRepositoryDetailScreen(id))
        }
    }

    fun navigateToBack() {
        viewModelScope.launch {
            _navigationEvent.emit(Destination.NavigateUp)
        }
    }

    fun changePage(newPage: Int) {
        _state.value = _state.value.copy(currentPage = newPage)
    }
}