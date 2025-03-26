package com.comp.lyricsapp.di

import com.comp.lyricsapp.data.remote.api.BarApi
import com.comp.lyricsapp.data.remote.api.LineApi
import com.comp.lyricsapp.data.remote.api.ProjectApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideBarApi(): BarApi {
        return object : BarApi {

        }
    }

    @Provides
    @Singleton
    fun provideLineApi(): LineApi {
        return object : LineApi {

        }
    }

    @Provides
    @Singleton
    fun provideProjectApi(): ProjectApi {
        return object : ProjectApi {

        }
    }
}
