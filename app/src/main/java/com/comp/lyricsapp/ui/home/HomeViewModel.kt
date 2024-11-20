package com.comp.lyricsapp.ui.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comp.lyricsapp.data.model.ProjectEntity
import com.comp.lyricsapp.data.repo.ProjectRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.lang.reflect.Constructor
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val projectRepositoryImpl: ProjectRepositoryImpl
) : ViewModel() {

    val projects: StateFlow<List<ProjectEntity>> = projectRepositoryImpl.getAll()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )


    fun createProject(projectEntity: ProjectEntity){
        viewModelScope.launch {
            projectRepositoryImpl.create(projectEntity)
        }
    }

}