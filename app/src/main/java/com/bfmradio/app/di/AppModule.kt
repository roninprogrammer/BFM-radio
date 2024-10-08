package com.bfmradio.app.di

import com.bfmradio.app.domain.usecases.GetPodcastsUseCase
import com.bfmradio.app.service.network.PodcastApiService
import com.bfmradio.app.service.network.RetrofitInstance
import com.bfmradio.app.service.repository.PodcastRepository
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
    fun providePodcastApiService(): PodcastApiService {
        return RetrofitInstance.api
    }

    @Provides
    @Singleton
    fun providePodcastRepository(apiService: PodcastApiService): PodcastRepository {
        return PodcastRepository(apiService)
    }

    @Provides
    @Singleton
    fun provideGetPodcastsUseCase(repository: PodcastRepository): GetPodcastsUseCase {
        return GetPodcastsUseCase(repository)
    }
}