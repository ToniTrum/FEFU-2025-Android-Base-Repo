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

    private fun getMyStars(
        perPage: Int = 10,
        page: Int = 1,
        search: String = ""
    ) {
        getMyStarsUseCase(
            perPage = perPage,
            page = page,
            starred = true,
            search = search
        ).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = MyStarsState(
                        gitRepositoryList = result.data?.gitRepositoryList ?: emptyList(),
                        hasNextPage = result.data?.hasNextPage ?: false
                    )
                }
                is Resource.Error -> {
                    _state.value = MyStarsState(error = result.message ?: "Unexpected error in MyStarsViewModel")
                }
                is Resource.Loading -> {
                    _state.value = MyStarsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun reloadData(
        perPage: Int = 10,
        page: Int = 1,
        search: String = ""
    ) {
        getMyStars(
            perPage = perPage,
            page = page,
            search = search
        )
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
}