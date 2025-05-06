package co.feip.fefu2025.domain.model

data class GitRepositoryDomain(
    val id: Int,
    val name: String,
    val description: String? = null,
    val starCount: Int = 0,
    val forkCount: Int = 0,
    val avatar: String? = null
)