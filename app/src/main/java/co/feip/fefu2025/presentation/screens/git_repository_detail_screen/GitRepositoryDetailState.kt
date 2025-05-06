package co.feip.fefu2025.presentation.screens.git_repository_detail_screen

import co.feip.fefu2025.common.BaseState
import co.feip.fefu2025.domain.model.GitRepositoryDetailDomain

data class GitRepositoryDetailState(
    override val isLoading: Boolean = false,
    val gitRepository: GitRepositoryDetailDomain? = null,
    override val error: String = ""
) : BaseState