package co.feip.fefu2025.domain.model

data class GitRepositoryListResultDomain(
    val gitRepositoryList: List<GitRepositoryDomain>,
    val hasNextPage: Boolean
)
