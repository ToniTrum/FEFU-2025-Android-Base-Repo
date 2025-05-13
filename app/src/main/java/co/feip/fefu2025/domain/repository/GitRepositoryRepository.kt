package co.feip.fefu2025.domain.repository

import co.feip.fefu2025.data.model.dto.GitRepositoryDetailDto
import co.feip.fefu2025.data.model.dto.GitRepositoryDto
import co.feip.fefu2025.data.model.dto.GitRepositoryListResultDto

interface GitRepositoryRepository {
    suspend fun getGitRepositoryList(
        perPage: Int,
        page: Int,
        starred: Boolean,
        search: String
    ): GitRepositoryListResultDto
    suspend fun getGitRepositoryDetail(gitRepositoryId: Int): GitRepositoryDetailDto
    suspend fun getLanguagesUsed(gitRepositoryId: Int): Map<String, Float>
    suspend fun starGitRepository(gitRepositoryId: Int): GitRepositoryDto
    suspend fun unstarGitRepository(gitRepositoryId: Int): GitRepositoryDto
}