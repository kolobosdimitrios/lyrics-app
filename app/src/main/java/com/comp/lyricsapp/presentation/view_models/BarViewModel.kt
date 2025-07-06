package com.comp.lyricsapp.presentation.view_models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comp.lyricsapp.domain.entities.Bar
import com.comp.lyricsapp.domain.entities.BarWithLines
import com.comp.lyricsapp.domain.usecases.BarUseCasesContainer
import com.comp.lyricsapp.domain.usecases.ProjectBarIds
import com.comp.lyricsapp.utils.ViewModelResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BarViewModel @Inject constructor(
    private val barUseCasesContainer: BarUseCasesContainer
): ViewModel() {

    private val _selectedBarLines = MutableStateFlow<List<BarWithLines>>(emptyList())
    val selectedBarLines: StateFlow<List<BarWithLines>> = _selectedBarLines.asStateFlow()

    private val _currentBarId = MutableStateFlow<Long?>(null)
    val currentBarId: StateFlow<Long?> = _currentBarId.asStateFlow()

    fun getBarsLines(barIds: Array<Long>){
        viewModelScope.launch {
            barUseCasesContainer.getBarsLinesUseCase(barIds, async = false)
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


    fun createBar(bar: Bar, onResult: (ViewModelResult.Success<Long>) -> Unit){
        viewModelScope.launch {
            val barId = barUseCasesContainer.createBarUseCase(bar, async = true)
            onResult(
                ViewModelResult.Success(barId)
            )
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