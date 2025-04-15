package co.feip.fefu2025.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Destination {
    @Serializable
    data object MainGraph: Destination

    @Serializable
    data class GitRepositoryDetailScreen(val id: Int): Destination

    @Serializable
    data object GitRepositoryListScreen: Destination

    @Serializable
    data object MyStarsScreen: Destination
}