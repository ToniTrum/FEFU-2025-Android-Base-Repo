package co.feip.fefu2025.presentation.screens.my_stars_screen

import co.feip.fefu2025.common.BaseState
import co.feip.fefu2025.domain.model.GitRepositoryDomain

data class MyStarsState(
    override val isLoading: Boolean = false,
    val gitRepositoryList: List<GitRepositoryDomain> = emptyList(),
    override val error: String = ""
) : BaseState