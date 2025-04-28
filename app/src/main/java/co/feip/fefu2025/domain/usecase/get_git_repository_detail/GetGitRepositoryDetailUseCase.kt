package co.feip.fefu2025.domain.usecase.get_git_repository_detail

import co.feip.fefu2025.common.Resource
import co.feip.fefu2025.data.remote.dto.toGitRepositoryDetail
import co.feip.fefu2025.domain.model.GitRepositoryDetail
import co.feip.fefu2025.domain.repository.GitRepositoryRepository
import coil.network.HttpException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetGitRepositoryDetailUseCase @Inject constructor(
    private val repository: GitRepositoryRepository
) {
    private var isFirstLaunch = true

    operator fun invoke(gitRepositoryId: Int): Flow<Resource<GitRepositoryDetail>> = flow {
        try {
            emit(Resource.Loading())
            val gitRepository = repository.getGitRepositoryDetail(gitRepositoryId).toGitRepositoryDetail()

            delay(3000)
            if (isFirstLaunch) {
                isFirstLaunch = false
                throw IOException()
            }

            emit(Resource.Success(gitRepository))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error in GetGitRepositoryDetailUseCase"))
        } catch (e: IOException) {
            emit(Resource.Error("Server connection lost"))
        }
    }
}