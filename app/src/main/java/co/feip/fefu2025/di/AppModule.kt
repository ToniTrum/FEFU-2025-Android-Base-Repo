package co.feip.fefu2025.di

import co.feip.fefu2025.BuildConfig
import co.feip.fefu2025.data.mapper.GitRepositoryMapper
import co.feip.fefu2025.data.remote.GitLabApiService
import co.feip.fefu2025.data.repository.GitRepositoryRepositoryImpl
import co.feip.fefu2025.domain.repository.GitRepositoryRepository
import co.feip.fefu2025.domain.usecase.get_git_repository_list.GetGitRepositoryListUseCase
import co.feip.fefu2025.domain.usecase.get_git_repository_detail.GetGitRepositoryDetailUseCase
import co.feip.fefu2025.domain.usecase.get_git_repository_list.GetMyStarsUseCase
import co.feip.fefu2025.domain.usecase.get_git_repository_list.SearchGitRepositoryUseCase
import co.feip.fefu2025.presentation.navigation.DefaultNavigator
import co.feip.fefu2025.presentation.navigation.Destination
import co.feip.fefu2025.presentation.navigation.Navigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideBaseUrl(): String = "https://gitlab.com/api/v4/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Private-Token", BuildConfig.GITLAB_API_TOKEN)
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    fun provideGitLabApiService(
        baseUrl: String, client: OkHttpClient
    ): GitLabApiService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(GitLabApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideNavigator(): Navigator {
        return DefaultNavigator(startDestination = Destination.MainGraph)
    }

    @Provides
    @Singleton
    fun provideGitRepositoryRepository(
        api: GitLabApiService
    ): GitRepositoryRepository {
        return GitRepositoryRepositoryImpl(api)
    }

    @Provides
    fun provideGetGitRepositoryListUseCase(
        repository: GitRepositoryRepository,
        mapper: GitRepositoryMapper
    ): GetGitRepositoryListUseCase {
        return GetGitRepositoryListUseCase(repository, mapper)
    }

    @Provides
    fun provideGetGitRepositoryDetailUseCase(
        repository: GitRepositoryRepository,
        mapper: GitRepositoryMapper
    ): GetGitRepositoryDetailUseCase {
        return GetGitRepositoryDetailUseCase(repository, mapper)
    }

    @Provides
    fun provideGetMyStarsUseCase(
        repository: GitRepositoryRepository,
        mapper: GitRepositoryMapper
    ): GetMyStarsUseCase {
        return GetMyStarsUseCase(repository, mapper)
    }

    @Provides
    fun provideSearchGitRepositoryUseCase(
        repository: GitRepositoryRepository,
        mapper: GitRepositoryMapper
    ): SearchGitRepositoryUseCase {
        return SearchGitRepositoryUseCase(repository, mapper)
    }
}