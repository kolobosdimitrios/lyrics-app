package com.comp.lyricsapp.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comp.lyricsapp.domain.entities.Project
import com.comp.lyricsapp.data.repo.ProjectRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel @Inject constructor(
    private val projectRepositoryImpl: ProjectRepositoryImpl
) : ViewModel() {

    val projects: StateFlow<List<Project>> = projectRepositoryImpl.getAll()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )


    fun createProject(project: Project){
        viewModelScope.launch {
            projectRepositoryImpl.create(project)
        }
    }

}