package co.feip.fefu2025.domain.usecase.star_git_repository

import co.feip.fefu2025.common.Resource
import co.feip.fefu2025.data.mapper.GitRepositoryMapper
import co.feip.fefu2025.domain.model.GitRepositoryDomain
import co.feip.fefu2025.domain.repository.GitRepositoryRepository
import coil.network.HttpException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class UnstarGitRepositoryUseCase @Inject constructor(
    private val repository: GitRepositoryRepository,
    private val mapper: GitRepositoryMapper
) {
    operator fun invoke(gitRepositoryId: Int): Flow<Resource<GitRepositoryDomain>> = flow {
        try {
            emit(Resource.Loading())
            val gitRepository = mapper.toGitRepositoryDomain(
                repository.unstarGitRepository(gitRepositoryId)
            )
            emit(Resource.Success(gitRepository))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error in UnstarGitRepositoryUseCase"))
        } catch (e: IOException) {
            emit(Resource.Error("Server connection lost"))
        }
    }
}