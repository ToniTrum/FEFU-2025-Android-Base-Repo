package co.feip.fefu2025.domain.model

import java.util.Date

data class GitRepositoryDetailDomain(
    val id: Int,
    val name: String,
    val description: String? = null,
    val starCount: Int = 0,
    val forkCount: Int = 0,
    val avatar: String? = null,
    val createdAt: String = Date().toString(),
    val languages: List<LanguageUsedDomain> = emptyList()
)