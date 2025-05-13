package co.feip.fefu2025.data.model.dto

data class GitRepositoryListResultDto(
    val gitRepositoryList: List<GitRepositoryDto>,
    val hasNextPage: Boolean
)
