package com.comp.lyricsapp.presentation.view_models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comp.lyricsapp.domain.entities.Project
import com.comp.lyricsapp.data.local.repo.LocalProjectRepository
import com.comp.lyricsapp.domain.entities.ProjectWithBars
import com.comp.lyricsapp.domain.usecases.ProjectUseCasesContainer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel @Inject constructor(
    private val projectUseCases: ProjectUseCasesContainer
) : ViewModel() {

    val projectsList: StateFlow<List<Project>> = projectUseCases.getAllProjectsUseCase(
        Unit,
        async = false
    ).stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    private val _selectedProject = MutableStateFlow<ProjectWithBars?>(null)  // Holds the selected project
    val selectedProjectWithBars: StateFlow<ProjectWithBars?> = _selectedProject.asStateFlow()

    fun getProjectWithBars(projectId: Long) {
        viewModelScope.launch {
            projectUseCases.getProjectWithBarsUseCase(projectId, false)
                .catch { e ->
                    Log.e("ProjectViewModel", "Error fetching project: ${e.message}")
                    _selectedProject.value = null
                }
                .collect{
                    projectWithBars -> _selectedProject.value = projectWithBars
                }
        }
    }

    fun createProject(project: Project){
        viewModelScope.launch {
            projectUseCases.createProjectUseCase(project, true)

        }
    }


    fun deleteProject(project: Project){
        viewModelScope.launch {
            projectUseCases.deleteProjectUseCase(project, true)
        }
    }

    fun updateProject(updatedProject: Project) {
        viewModelScope.launch {
            projectUseCases.updateProjectUseCase(updatedProject)
        }
    }

}