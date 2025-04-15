package co.feip.fefu2025.di.mock

import co.feip.fefu2025.data.remote.dto.GitRepositoryDetailDto
import co.feip.fefu2025.data.remote.dto.GitRepositoryDto
import co.feip.fefu2025.domain.repository.GitRepositoryRepository
import java.util.Date

class FakeGitRepositoryRepository: GitRepositoryRepository {
    override suspend fun getGitRepositoryList(): List<GitRepositoryDto> {
        return listOf(
            GitRepositoryDto(
                id = 1,
                name = "Cool Repo 1",
                description = "My fake repo",
                starCount = 1,
                forkCount = 1,
                avatar = null
            ),
            GitRepositoryDto(
                id = 2,
                name = "Cool Repo 2",
                description = "My fake repo",
                starCount = 2,
                forkCount = 2,
                avatar = null
            )
        )
    }

    override suspend fun getGitRepositoryDetail(gitRepositoryId: Int): GitRepositoryDetailDto {
        return GitRepositoryDetailDto(
            id = gitRepositoryId,
            name = "Cool Repo $gitRepositoryId",
            description = "My fake repo",
            starCount = gitRepositoryId,
            forkCount = gitRepositoryId,
            avatar = null,
            languages = listOf("Kotlin" to 60f, "Java" to 40f),
            createdAt = Date()
        )
    }

    override suspend fun getMyStars(): List<GitRepositoryDto> {
        return listOf(
            GitRepositoryDto(
                id = 1,
                name = "Cool Repo 1",
                description = "My fake repo",
                starCount = 1,
                forkCount = 1,
                avatar = null
            ),
            GitRepositoryDto(
                id = 2,
                name = "Cool Repo 2",
                description = "My fake repo",
                starCount = 2,
                forkCount = 2,
                avatar = null
            )
        )
    }
}