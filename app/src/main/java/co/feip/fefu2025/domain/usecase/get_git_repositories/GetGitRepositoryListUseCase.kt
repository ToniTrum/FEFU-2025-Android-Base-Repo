package co.feip.fefu2025.domain.usecase.get_git_repositories

import co.feip.fefu2025.common.Resource
import co.feip.fefu2025.data.remote.dto.toGitRepository
import co.feip.fefu2025.domain.model.GitRepository
import co.feip.fefu2025.domain.repository.GitRepositoryRepository
import coil.network.HttpException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetGitRepositoryListUseCase @Inject constructor(
    private val repository: GitRepositoryRepository
) {
    operator fun invoke(): Flow<Resource<List<GitRepository>>> = flow {
        try {
            emit(Resource.Loading())
            val gitRepositories = repository.getGitRepositoryList().map { it.toGitRepository() }
            emit(Resource.Success(gitRepositories))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error in GetGitRepositoryListUseCase"))
        } catch (e: IOException) {
            emit(Resource.Error("Server connection lost"))
        }
    }
}