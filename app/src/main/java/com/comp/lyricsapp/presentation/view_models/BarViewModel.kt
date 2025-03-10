package com.comp.lyricsapp.presentation.view_models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comp.lyricsapp.domain.entities.Bar
import com.comp.lyricsapp.domain.entities.Line
import com.comp.lyricsapp.domain.usecases.BarUseCasesContainer
import com.comp.lyricsapp.domain.usecases.ProjectBarIds
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class BarViewModel @Inject constructor(
    private val barUseCasesContainer: BarUseCasesContainer
): ViewModel() {

    private val _selectedBarLines = MutableStateFlow<List<Line>>(emptyList())
    val selectedBarLines: StateFlow<List<Line>> = _selectedBarLines.asStateFlow()

    fun getBarLines(barId: Long){
        viewModelScope.launch {
            barUseCasesContainer.getBarLinesUseCase(barId, async = false)
                .catch { e ->
                    //Catch error
                    Log.e("BarViewModel", "Unable to get lines for bar error: ${e.message}")
                    _selectedBarLines.value = emptyList()
                }
                .collect{
                    barWithLines -> _selectedBarLines.value = barWithLines
                }
        }
    }

    fun createBar(bar: Bar){
        viewModelScope.launch {
            barUseCasesContainer.createBarUseCase(bar, async = true)
        }
    }

    fun updateBar(bar: Bar){
        viewModelScope.launch {
            barUseCasesContainer.updateBarUseCase(bar, async = true)
        }
    }

    fun deleteProjectBar(projectBarIds: ProjectBarIds){
        viewModelScope.launch {
            barUseCasesContainer.deleteProjectBar(projectBarIds, async = true)
        }
    }

    fun deleteProjectBars(projectId: Long){
        viewModelScope.launch {
            barUseCasesContainer.deleteProjectBars(projectId, async = true)
        }
    }


}