package co.feip.fefu2025.data.remote.dto

import co.feip.fefu2025.domain.model.GitRepositoryDetail
import java.util.Date

data class GitRepositoryDetailDto(
    val id: Int,
    val name: String,
    val description: String? = null,
    val starCount: Int = 0,
    val forkCount: Int = 0,
    val avatar: String? = null,
    val languages: List<Pair<String, Float>> = emptyList(),
    val createdAt: Date = Date()
)

fun GitRepositoryDetailDto.toGitRepositoryDetail(): GitRepositoryDetail {
    return GitRepositoryDetail(
        id = id,
        name = name,
        description = description,
        starCount = starCount,
        forkCount = forkCount,
        avatar = avatar,
        languages = languages,
        createdAt = createdAt
    )
}