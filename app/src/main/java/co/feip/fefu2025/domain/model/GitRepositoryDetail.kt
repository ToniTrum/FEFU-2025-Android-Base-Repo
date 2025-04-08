package co.feip.fefu2025.domain.model

import java.util.Date

data class GitRepositoryDetail(
    val id: Int,
    val name: String,
    val description: String? = null,
    val starCount: Int = 0,
    val forkCount: Int = 0,
    val avatar: String? = null,
    val languages: List<Pair<String, Float>> = emptyList(),
    val createdAt: Date = Date()
)