package co.feip.fefu2025.di

import co.feip.fefu2025.di.mock.FakeGitRepositoryRepository
import co.feip.fefu2025.domain.repository.GitRepositoryRepository
import co.feip.fefu2025.domain.usecase.get_git_repository_list.GetGitRepositoryListUseCase
import co.feip.fefu2025.domain.usecase.get_git_repository_detail.GetGitRepositoryDetailUseCase
import co.feip.fefu2025.presentation.navigation.DefaultNavigator
import co.feip.fefu2025.presentation.navigation.Destination
import co.feip.fefu2025.presentation.navigation.Navigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNavigator(): Navigator {
        return DefaultNavigator(startDestination = Destination.MainGraph)
    }

    @Provides
    @Singleton
    fun provideGitRepositoryRepository(): GitRepositoryRepository {
        return FakeGitRepositoryRepository()
    }

    @Provides
    fun provideGetGitRepositoryListUseCase(
        repository: GitRepositoryRepository
    ): GetGitRepositoryListUseCase {
        return GetGitRepositoryListUseCase(repository)
    }

    @Provides
    fun provideGetGitRepositoryDetailUseCase(
        repository: GitRepositoryRepository
    ): GetGitRepositoryDetailUseCase {
        return GetGitRepositoryDetailUseCase(repository)
    }
}