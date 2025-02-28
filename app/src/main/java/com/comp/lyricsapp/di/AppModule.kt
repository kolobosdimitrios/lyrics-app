package com.comp.lyricsapp.di

import android.content.Context

import androidx.room.Room
import com.comp.lyricsapp.data.local.database.MainDatabase
import com.comp.lyricsapp.data.local.dao.ProjectDAO
import com.comp.lyricsapp.data.local.repo.LocalProjectRepository
import com.comp.lyricsapp.data.remote.api.ProjectApi
import com.comp.lyricsapp.data.remote.repo.RemoteProjectRepository
import com.comp.lyricsapp.data.repo.ProjectRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getDatabaseInstance(@ApplicationContext context: Context) : MainDatabase {

        return Room.databaseBuilder(context, MainDatabase::class.java, "main_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideProjectDao(mainDatabase: MainDatabase) : ProjectDAO {
        return mainDatabase.projectDAO()
    }

    @Singleton
    @Provides
    fun provideLocalProjectRepository(projectDAO: ProjectDAO) : LocalProjectRepository {
        return LocalProjectRepository(projectDAO)
    }
    @Provides
    fun provideProjectApi(): ProjectApi {
        return object : ProjectApi{ }
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
    ) : ProjectRepositoryImpl {
        return ProjectRepositoryImpl(
            localProjectRepository = localProjectRepository,
            remoteProjectRepository = remoteProjectRepository
        )
    }


}