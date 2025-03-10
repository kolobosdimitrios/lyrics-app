package com.comp.lyricsapp.di

import com.comp.lyricsapp.data.repo.BarRepositoryImpl
import com.comp.lyricsapp.data.repo.LineRepositoryImpl
import com.comp.lyricsapp.data.repo.ProjectRepositoryImpl
import com.comp.lyricsapp.domain.usecases.BarUseCasesContainer
import com.comp.lyricsapp.domain.usecases.CreateBar
import com.comp.lyricsapp.domain.usecases.CreateLineUseCase
import com.comp.lyricsapp.domain.usecases.CreateProjectUseCase
import com.comp.lyricsapp.domain.usecases.DeleteBarLineUseCase
import com.comp.lyricsapp.domain.usecases.DeleteProjectBar
import com.comp.lyricsapp.domain.usecases.DeleteProjectBars
import com.comp.lyricsapp.domain.usecases.DeleteProjectUseCase
import com.comp.lyricsapp.domain.usecases.GetAllProjectsUseCase
import com.comp.lyricsapp.domain.usecases.GetBarLinesUseCase
import com.comp.lyricsapp.domain.usecases.GetProjectWithBarsUseCase
import com.comp.lyricsapp.domain.usecases.LineUseCasesContainer
import com.comp.lyricsapp.domain.usecases.ProjectUseCasesContainer
import com.comp.lyricsapp.domain.usecases.UpdateBar
import com.comp.lyricsapp.domain.usecases.UpdateLineUseCase
import com.comp.lyricsapp.domain.usecases.UpdateProjectUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    @Singleton
    fun provideProjectUseCasesContainer(repositoryImpl: ProjectRepositoryImpl) : ProjectUseCasesContainer {
        return ProjectUseCasesContainer(
            createProjectUseCase = CreateProjectUseCase(repositoryImpl),
            updateProjectUseCase = UpdateProjectUseCase(repositoryImpl),
            deleteProjectUseCase = DeleteProjectUseCase(repositoryImpl),
            getAllProjectsUseCase = GetAllProjectsUseCase(repositoryImpl),
            getProjectWithBarsUseCase = GetProjectWithBarsUseCase(repositoryImpl)
        )
    }

    @Provides
    @Singleton
    fun provideLineUseCasesContainer(repository: LineRepositoryImpl): LineUseCasesContainer{
        return LineUseCasesContainer(
            createLineUseCase = CreateLineUseCase(repository),
            updateLineUseCase = UpdateLineUseCase(repository),
            deleteBarLineUseCase = DeleteBarLineUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideBarUseCases(repository: BarRepositoryImpl): BarUseCasesContainer {
        return BarUseCasesContainer(
            createBarUseCase = CreateBar(repository),
            updateBarUseCase = UpdateBar(repository),
            deleteProjectBar = DeleteProjectBar(repository),
            deleteProjectBars = DeleteProjectBars(repository),
            getBarLinesUseCase = GetBarLinesUseCase(repository)
        )
    }
}