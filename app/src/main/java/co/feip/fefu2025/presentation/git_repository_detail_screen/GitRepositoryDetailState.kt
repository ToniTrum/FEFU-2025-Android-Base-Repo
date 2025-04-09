package co.feip.fefu2025.presentation.git_repository_detail_screen

import co.feip.fefu2025.domain.model.GitRepositoryDetail

data class GitRepositoryDetailState(
    val isLoading: Boolean = false,
    val gitRepository: GitRepositoryDetail? = null,
    val error: String = ""
)