package com.comp.lyricsapp.di

import android.content.Context
import androidx.room.Room
import com.comp.lyricsapp.data.local.dao.BarDao
import com.comp.lyricsapp.data.local.dao.LineDao
import com.comp.lyricsapp.data.local.dao.ProjectDAO
import com.comp.lyricsapp.data.local.database.MainDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) : MainDatabase {

        return Room.databaseBuilder(context, MainDatabase::class.java, "main_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideProjectDao(mainDatabase: MainDatabase) : ProjectDAO {
        return mainDatabase.projectDAO()
    }

    @Provides
    fun provideBarDao(mainDatabase: MainDatabase) : BarDao {
        return mainDatabase.barDAO()
    }

    @Provides
    fun provideLineDao(mainDatabase: MainDatabase) : LineDao {
        return mainDatabase.lineDAO()
    }
}