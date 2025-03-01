package com.comp.lyricsapp.presentation.view_models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comp.lyricsapp.domain.entities.Project
import com.comp.lyricsapp.data.local.repo.LocalProjectRepository
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
    private val projectRepositoryImpl: LocalProjectRepository
) : ViewModel() {

    val projectsList: StateFlow<List<Project>> = projectRepositoryImpl.getAll()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    private val _selectedProject = MutableStateFlow<Project?>(null)  // Holds the selected project
    val selectedProject: StateFlow<Project?> = _selectedProject.asStateFlow()

    fun getProject(projectId: Long) {
        viewModelScope.launch {
            projectRepositoryImpl.get(projectId)
                .catch { error -> error.message?.let { Log.e("ProjectViewModel", it) } }
                .collect { project ->
                    _selectedProject.value = project  // Updates StateFlow
                }
        }
    }

    fun createProject(project: Project){
        viewModelScope.launch {
            projectRepositoryImpl.create(project)
        }
    }

    fun deleteProjects() {
        viewModelScope.launch {
            projectRepositoryImpl.deleteAll()
        }
    }

    fun deleteProject(project: Project){
        viewModelScope.launch {
            projectRepositoryImpl.delete(project)
        }
    }

    fun updateProject(updatedProject: Project) {
        viewModelScope.launch {
            projectRepositoryImpl.update(updatedProject)
        }
    }

}