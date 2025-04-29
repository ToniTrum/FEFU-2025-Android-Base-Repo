package co.feip.fefu2025.domain.usecase.search_git_repository

import co.feip.fefu2025.common.Resource
import co.feip.fefu2025.data.remote.dto.toGitRepository
import co.feip.fefu2025.domain.model.GitRepository
import co.feip.fefu2025.domain.repository.GitRepositoryRepository
import coil.network.HttpException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class SearchGitRepositoryUseCase @Inject constructor(
    private val repository: GitRepositoryRepository
) {
    operator fun invoke(query: String): Flow<Resource<List<GitRepository>>> = flow {
        try {
            emit(Resource.Loading())
            val gitRepositoryList = repository.searchGitRepository(query).map { it.toGitRepository() }
            emit(Resource.Success(gitRepositoryList))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error in SearchGitRepositoryUseCase"))
        } catch (e: IOException) {
            emit(Resource.Error("Server connection lost"))
        }
    }
}