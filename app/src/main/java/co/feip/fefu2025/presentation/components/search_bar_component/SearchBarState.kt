package co.feip.fefu2025.presentation.components.search_bar_component

import co.feip.fefu2025.common.BaseState
import co.feip.fefu2025.domain.model.GitRepositoryDomain

data class SearchBarState(
    override val isLoading: Boolean = false,
    val gitRepositoryList: List<GitRepositoryDomain> = emptyList(),
    override val error: String = ""
) : BaseState