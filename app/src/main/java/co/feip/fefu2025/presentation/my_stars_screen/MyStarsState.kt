package co.feip.fefu2025.presentation.my_stars_screen

import co.feip.fefu2025.domain.model.GitRepository

data class MyStarsState(
    val isLoading: Boolean = false,
    val gitRepositoryList: List<GitRepository> = emptyList(),
    val error: String = ""
)