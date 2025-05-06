package co.feip.fefu2025.data.mapper

import co.feip.fefu2025.data.model.dto.GitRepositoryDetailDto
import co.feip.fefu2025.data.model.dto.GitRepositoryDto
import co.feip.fefu2025.domain.model.GitRepositoryDetailDomain
import co.feip.fefu2025.domain.model.GitRepositoryDomain
import co.feip.fefu2025.domain.model.LanguageUsedDomain
import javax.inject.Inject

class GitRepositoryMapper @Inject constructor() {
    fun toGitRepositoryDomain(
        gitRepositoryDto: GitRepositoryDto
    ): GitRepositoryDomain {
        return GitRepositoryDomain(
            id = gitRepositoryDto.id,
            name = gitRepositoryDto.name,
            description = gitRepositoryDto.description,
            starCount = gitRepositoryDto.starCount,
            forkCount = gitRepositoryDto.forksCount,
            avatar = gitRepositoryDto.avatarUrl
        )
    }

    fun toGitRepositoryDetailDomain(
        gitRepositoryDetailDto: GitRepositoryDetailDto,
        languages: Map<String, Float>?
    ): GitRepositoryDetailDomain {
        return GitRepositoryDetailDomain(
            id = gitRepositoryDetailDto.id,
            name = gitRepositoryDetailDto.name,
            description = gitRepositoryDetailDto.description,
            starCount = gitRepositoryDetailDto.starCount,
            forkCount = gitRepositoryDetailDto.forksCount,
            avatar = gitRepositoryDetailDto.avatarUrl,
            createdAt = gitRepositoryDetailDto.createdAt,
            languages = languages?.map {
                LanguageUsedDomain(it.key, it.value)
            } ?: emptyList()
        )
    }
}