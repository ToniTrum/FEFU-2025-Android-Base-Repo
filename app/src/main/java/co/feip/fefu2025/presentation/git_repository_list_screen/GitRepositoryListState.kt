package co.feip.fefu2025.presentation.git_repository_list_screen

import co.feip.fefu2025.domain.model.GitRepository

data class GitRepositoryListState(
    val isLoading: Boolean = false,
    val gitRepositoryList: List<GitRepository> = emptyList(),
    val error: String = ""
)