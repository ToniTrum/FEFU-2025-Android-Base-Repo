package co.feip.fefu2025.domain.usecase.get_my_stars

import co.feip.fefu2025.common.Resource
import co.feip.fefu2025.data.remote.dto.toGitRepository
import co.feip.fefu2025.domain.model.GitRepository
import co.feip.fefu2025.domain.repository.GitRepositoryRepository
import coil.network.HttpException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetMyStarsUseCase @Inject constructor(
    private val repository: GitRepositoryRepository
) {
    private var isFirstLaunch = true

    operator fun invoke(): Flow<Resource<List<GitRepository>>> = flow {
        try {
            emit(Resource.Loading())
            val myStars = repository.getMyStars().map { it.toGitRepository() }

            delay(3000)
            if (isFirstLaunch) {
                isFirstLaunch = false
                throw IOException()
            }

            emit(Resource.Success(myStars))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error in GetMyStarsUseCase"))
        } catch (e: IOException) {
            emit(Resource.Error("Server connection lost"))
        }
    }
}