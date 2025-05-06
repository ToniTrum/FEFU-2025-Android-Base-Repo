package co.feip.fefu2025.presentation.screens.git_repository_list_screen

import co.feip.fefu2025.common.BaseState
import co.feip.fefu2025.domain.model.GitRepositoryDomain

data class GitRepositoryListState(
    override val isLoading: Boolean = false,
    val gitRepositoryList: List<GitRepositoryDomain> = emptyList(),
    override val error: String = ""
) : BaseState