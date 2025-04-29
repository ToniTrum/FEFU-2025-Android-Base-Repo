package co.feip.fefu2025.presentation.components.search_bar_component

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.feip.fefu2025.common.Resource
import co.feip.fefu2025.domain.usecase.search_git_repository.SearchGitRepositoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchBarViewModel @Inject constructor(
    private val searchGitRepositoryUseCase: SearchGitRepositoryUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(SearchBarState())
    val state: StateFlow<SearchBarState> = _state

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    init {
        searchGitRepository()
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    private fun searchGitRepository() {
        searchQuery
            .debounce(300).onEach { query ->
                searchGitRepositoryUseCase(query).collect { result ->
                    when(result) {
                        is Resource.Success -> {
                            _state.value = SearchBarState(gitRepositoryList = result.data ?: emptyList())
                        }
                        is Resource.Error -> {
                            _state.value = SearchBarState(error = result.message ?: "Unexpected error in SearchViewModel")
                        }
                        is Resource.Loading -> {
                            _state.value = SearchBarState(isLoading = true)
                        }
                    }
                }
            }.launchIn(viewModelScope)
    }
}