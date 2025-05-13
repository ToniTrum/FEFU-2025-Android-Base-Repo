package co.feip.fefu2025.domain.repository

import co.feip.fefu2025.data.model.dto.GitRepositoryDetailDto
import co.feip.fefu2025.data.model.dto.GitRepositoryDto

interface GitRepositoryRepository {
    suspend fun getGitRepositoryList(
        perPage: Int,
        page: Int,
        starred: Boolean,
        search: String
    ): List<GitRepositoryDto>
    suspend fun getGitRepositoryDetail(gitRepositoryId: Int): GitRepositoryDetailDto
    suspend fun getLanguagesUsed(gitRepositoryId: Int): Map<String, Float>
}