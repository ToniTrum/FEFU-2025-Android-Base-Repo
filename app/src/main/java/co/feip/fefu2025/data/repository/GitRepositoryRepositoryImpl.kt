package co.feip.fefu2025.data.repository

import co.feip.fefu2025.data.model.dto.GitRepositoryDetailDto
import co.feip.fefu2025.data.model.dto.GitRepositoryDto
import co.feip.fefu2025.data.model.dto.GitRepositoryListResultDto
import co.feip.fefu2025.data.remote.GitLabApiService
import co.feip.fefu2025.domain.repository.GitRepositoryRepository
import retrofit2.Response
import javax.inject.Inject

class GitRepositoryRepositoryImpl @Inject constructor(
    private val api: GitLabApiService
): GitRepositoryRepository {
    override suspend fun getGitRepositoryList(
        perPage: Int,
        page: Int,
        starred: Boolean,
        search: String
    ): GitRepositoryListResultDto {
        return handleResponse(
            response = api.getGitRepositoryList(
                perPage = perPage,
                page = page,
                starred = starred,
                search = search
            )
        )
    }

    override suspend fun getGitRepositoryDetail(gitRepositoryId: Int): GitRepositoryDetailDto {
        return api.getGitRepositoryDetail(gitRepositoryId)
    }

    override suspend fun getLanguagesUsed(gitRepositoryId: Int): Map<String, Float> {
        return api.getLanguagesUsed(gitRepositoryId)
    }

    override suspend fun starGitRepository(gitRepositoryId: Int): GitRepositoryDto {
        val response = api.starGitRepository(gitRepositoryId)
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Empty response body")
        }
        else {
            throw Exception("Failed to star project: ${response.message()}")
        }
    }

    override suspend fun unstarGitRepository(gitRepositoryId: Int): GitRepositoryDto {
        val response = api.unstarGitRepository(gitRepositoryId)
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Empty response body")
        }
        else {
            throw Exception("Failed to star project: ${response.message()}")
        }
    }

    private fun handleResponse(response: Response<List<GitRepositoryDto>>): GitRepositoryListResultDto {
        if (response.isSuccessful) {
            val gitRepositoryList = response.body() ?: emptyList()
            val hasNextPage = response.headers()["X-Next-Page"].isNullOrEmpty()
            return GitRepositoryListResultDto(gitRepositoryList, hasNextPage)
        } else {
            throw Exception("Failed to fetch repositories: ${response.message()}")
        }
    }
}