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

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context = context
    


}