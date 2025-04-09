package co.feip.fefu2025.data.remote.dto

import co.feip.fefu2025.domain.model.GitRepository

data class GitRepositoryDto (
    val id: Int,
    val name: String,
    val description: String? = null,
    val starCount: Int = 0,
    val forkCount: Int = 0,
    val avatar: String? = null
)

fun GitRepositoryDto.toGitRepository(): GitRepository {
    return GitRepository(
        id = id,
        name = name,
        description = description,
        starCount = starCount,
        forkCount = forkCount,
        avatar = avatar
    )
}