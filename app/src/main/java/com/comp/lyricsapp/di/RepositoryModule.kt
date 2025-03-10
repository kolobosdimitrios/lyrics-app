package com.comp.lyricsapp.di

import com.comp.lyricsapp.data.local.dao.BarDao
import com.comp.lyricsapp.data.local.dao.LineDao
import com.comp.lyricsapp.data.local.dao.ProjectDAO
import com.comp.lyricsapp.data.local.repo.LocalBarRepository
import com.comp.lyricsapp.data.local.repo.LocalLineRepository
import com.comp.lyricsapp.data.local.repo.LocalProjectRepository
import com.comp.lyricsapp.data.remote.api.BarApi
import com.comp.lyricsapp.data.remote.api.LineApi
import com.comp.lyricsapp.data.remote.api.ProjectApi
import com.comp.lyricsapp.data.remote.repo.RemoteBarRepository
import com.comp.lyricsapp.data.remote.repo.RemoteLineRepository
import com.comp.lyricsapp.data.remote.repo.RemoteProjectRepository
import com.comp.lyricsapp.data.repo.BarRepositoryImpl
import com.comp.lyricsapp.data.repo.LineRepositoryImpl
import com.comp.lyricsapp.data.repo.ProjectRepositoryImpl
import com.comp.lyricsapp.domain.repositories.BarRepository
import com.comp.lyricsapp.domain.repositories.LineRepository
import com.comp.lyricsapp.domain.repositories.ProjectRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProjectRepositoryModule {

    @Singleton
    @Provides
    fun provideLocalProjectRepository(projectDAO: ProjectDAO): LocalProjectRepository {
        return LocalProjectRepository(projectDAO)
    }


    @Singleton
    @Provides
    fun provideRemoteProjectRepository(projectApi: ProjectApi): RemoteProjectRepository {
        return RemoteProjectRepository(projectApi)
    }

    @Singleton
    @Provides
    fun provideProjectRepository(
        localProjectRepository: LocalProjectRepository,
        remoteProjectRepository: RemoteProjectRepository
    ): ProjectRepository {
        return ProjectRepositoryImpl(
            localProjectRepository = localProjectRepository,
            remoteProjectRepository = remoteProjectRepository
        )
    }

}

@Module
@InstallIn(SingletonComponent::class)
object LineRepositoryModule{
    @Singleton
    @Provides
    fun provideLocalLineRepository(lineDao: LineDao) : LocalLineRepository {
        return LocalLineRepository(lineDao)
    }


    @Singleton
    @Provides
    fun provideRemoteLineRepository(lineApi: LineApi): RemoteLineRepository {
        return RemoteLineRepository(lineApi)
    }

    @Singleton
    @Provides
    fun provideLineRepository(
        localLineRepository: LocalLineRepository,
        remoteLineRepository: RemoteLineRepository
    ) : LineRepository {
        return LineRepositoryImpl(
            localLineRepository = localLineRepository,
            remoteLineRepository = remoteLineRepository
        )
    }
}


@Module
@InstallIn(SingletonComponent::class)
object BarRepositoryModule {
    @Singleton
    @Provides
    fun provideLocalBarRepository(barDao: BarDao) : LocalBarRepository {
        return LocalBarRepository(barDao)
    }


    @Singleton
    @Provides
    fun provideRemoteBarRepository(barApi: BarApi): RemoteBarRepository {
        return RemoteBarRepository(barApi)
    }

    @Singleton
    @Provides
    fun provideBarRepository(
        localBarRepository: LocalBarRepository,
        remoteBarRepository: RemoteBarRepository
    ) : BarRepository {
        return BarRepositoryImpl(
            localBarRepository = localBarRepository,
            remoteBarRepository = remoteBarRepository
        )
    }
}