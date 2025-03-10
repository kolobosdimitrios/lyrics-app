package com.comp.lyricsapp.di

import com.comp.lyricsapp.data.remote.api.ProjectApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideProjectApi(): ProjectApi {
        return object : ProjectApi { }
    }
}