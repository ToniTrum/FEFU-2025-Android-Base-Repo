package co.feip.fefu2025.domain.repository

import co.feip.fefu2025.data.remote.dto.GitRepositoryDetailDto
import co.feip.fefu2025.data.remote.dto.GitRepositoryDto

interface GitRepositoryRepository {
    suspend fun getGitRepositoryList(): List<GitRepositoryDto>
    suspend fun getGitRepositoryDetail(gitRepositoryId: Int): GitRepositoryDetailDto
    suspend fun getMyStars(): List<GitRepositoryDto>
}