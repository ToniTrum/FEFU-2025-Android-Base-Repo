package co.feip.fefu2025.data.repository

import co.feip.fefu2025.data.model.dto.GitRepositoryDetailDto
import co.feip.fefu2025.data.model.dto.GitRepositoryDto
import co.feip.fefu2025.data.remote.GitLabApiService
import co.feip.fefu2025.domain.repository.GitRepositoryRepository
import javax.inject.Inject

class GitRepositoryRepositoryImpl @Inject constructor(
    private val api: GitLabApiService
): GitRepositoryRepository {
    override suspend fun getGitRepositoryList(): List<GitRepositoryDto> {
        return api.getGitRepositoryList()
    }

    override suspend fun getGitRepositoryDetail(gitRepositoryId: Int): GitRepositoryDetailDto {
        return api.getGitRepositoryDetail(gitRepositoryId)
    }

    override suspend fun getLanguagesUsed(gitRepositoryId: Int): Map<String, Float> {
        return api.getLanguagesUsed(gitRepositoryId)
    }

    override suspend fun getMyStars(): List<GitRepositoryDto> {
        return api.getGitRepositoryList(starred = true)
    }

    override suspend fun searchGitRepository(query: String): List<GitRepositoryDto> {
        return api.getGitRepositoryList(search = query)
    }
}