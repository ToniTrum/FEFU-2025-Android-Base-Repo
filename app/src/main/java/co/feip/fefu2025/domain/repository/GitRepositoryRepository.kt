package co.feip.fefu2025.domain.repository

import co.feip.fefu2025.data.model.dto.GitRepositoryDetailDto
import co.feip.fefu2025.data.model.dto.GitRepositoryDto

interface GitRepositoryRepository {
    suspend fun getGitRepositoryList(): List<GitRepositoryDto>
    suspend fun getGitRepositoryDetail(gitRepositoryId: Int): GitRepositoryDetailDto
    suspend fun getLanguagesUsed(gitRepositoryId: Int): Map<String, Float>
    suspend fun getMyStars(): List<GitRepositoryDto>
    suspend fun searchGitRepository(query: String): List<GitRepositoryDto>
}