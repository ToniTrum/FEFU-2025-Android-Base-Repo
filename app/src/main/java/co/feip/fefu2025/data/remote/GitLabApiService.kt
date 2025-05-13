package co.feip.fefu2025.data.remote

import co.feip.fefu2025.data.model.dto.GitRepositoryDetailDto
import co.feip.fefu2025.data.model.dto.GitRepositoryDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface GitLabApiService {
    @GET("projects")
    suspend fun getGitRepositoryList(
        @Query("per_page") perPage: Int = 20,
        @Query("page") page: Int = 1,
        @Query("starred") starred: Boolean = false,
        @Query("search") search: String = ""
    ): Response<List<GitRepositoryDto>>

    @GET("projects/{git_repository_id}")
    suspend fun getGitRepositoryDetail(
        @Path("git_repository_id") gitRepositoryId: Int
    ): GitRepositoryDetailDto

    @GET("projects/{git_repository_id}/languages")
    suspend fun getLanguagesUsed(
        @Path("git_repository_id") gitRepositoryId: Int
    ): Map<String, Float>

    @POST("projects/{git_repository_id}/star")
    suspend fun starGitRepository(
        @Path("git_repository_id") gitRepositoryId: Int
    ): Response<GitRepositoryDto>

    @POST("projects/{git_repository_id}/unstar")
    suspend fun unstarGitRepository(
        @Path("git_repository_id") gitRepositoryId: Int
    ): Response<GitRepositoryDto>
}