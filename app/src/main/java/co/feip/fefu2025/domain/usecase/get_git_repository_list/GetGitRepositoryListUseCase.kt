package co.feip.fefu2025.domain.usecase.get_git_repository_list

import co.feip.fefu2025.common.Resource
import co.feip.fefu2025.data.mapper.GitRepositoryMapper
import co.feip.fefu2025.domain.model.GitRepositoryDomain
import co.feip.fefu2025.domain.repository.GitRepositoryRepository
import coil.network.HttpException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetGitRepositoryListUseCase @Inject constructor(
    private val repository: GitRepositoryRepository,
    private val mapper: GitRepositoryMapper
) {
    operator fun invoke(): Flow<Resource<List<GitRepositoryDomain>>> = flow {
        try {
            emit(Resource.Loading())
            val gitRepositoryList = repository.getGitRepositoryList().map {
                mapper.toGitRepositoryDomain(it)
            }
            emit(Resource.Success(gitRepositoryList))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error in GetGitRepositoryListUseCase"))
        } catch (e: IOException) {
            emit(Resource.Error("Server connection lost"))
        }
    }
}