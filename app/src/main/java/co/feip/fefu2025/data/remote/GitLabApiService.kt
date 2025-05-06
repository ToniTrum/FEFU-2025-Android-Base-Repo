package co.feip.fefu2025.data.remote

import co.feip.fefu2025.data.model.dto.GitRepositoryDetailDto
import co.feip.fefu2025.data.model.dto.GitRepositoryDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitLabApiService {
    @GET("projects")
    suspend fun getGitRepositoryList(
        @Query("visibility") visibility: String = "public",
        @Query("per_page") perPage: Int = 20,
        @Query("page") page: Int = 1,
        @Query("starred") starred: Boolean = false,
        @Query("search") search: String = ""
    ): List<GitRepositoryDto>

    @GET("projects/{git_repository_id}")
    suspend fun getGitRepositoryDetail(
        @Path("git_repository_id") gitRepositoryId: Int
    ): GitRepositoryDetailDto

    @GET("projects/{git_repository_id}/languages")
    suspend fun getLanguagesUsed(
        @Path("git_repository_id") gitRepositoryId: Int
    ): Map<String, Float>
}