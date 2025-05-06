package co.feip.fefu2025.domain.usecase.get_git_repository_detail

import android.util.Log
import co.feip.fefu2025.common.Resource
import co.feip.fefu2025.data.mapper.GitRepositoryMapper
import co.feip.fefu2025.domain.model.GitRepositoryDetailDomain
import co.feip.fefu2025.domain.repository.GitRepositoryRepository
import coil.network.HttpException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetGitRepositoryDetailUseCase @Inject constructor(
    private val repository: GitRepositoryRepository,
    private val mapper: GitRepositoryMapper
) {
    operator fun invoke(gitRepositoryId: Int): Flow<Resource<GitRepositoryDetailDomain>> = flow {
        try {
            emit(Resource.Loading())
            val gitRepository = mapper.toGitRepositoryDetailDomain(
                repository.getGitRepositoryDetail(gitRepositoryId),
                repository.getLanguagesUsed(gitRepositoryId)
            )
            Log.d("GetGitRepositoryDetailUseCase", "${repository.getLanguagesUsed(gitRepositoryId)}")
            emit(Resource.Success(gitRepository))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error in GetGitRepositoryDetailUseCase"))
        } catch (e: IOException) {
            emit(Resource.Error("Server connection lost"))
        }
    }
}