package com.comp.lyricsapp.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comp.lyricsapp.domain.entities.Line
import com.comp.lyricsapp.domain.usecases.BarLineIds
import com.comp.lyricsapp.domain.usecases.LineUseCasesContainer
import kotlinx.coroutines.launch
import javax.inject.Inject

class LineViewModel @Inject constructor(
    private val lineUseCasesContainer: LineUseCasesContainer
): ViewModel() {

    fun createLine(newLine: Line){
        viewModelScope.launch {
            lineUseCasesContainer.createLineUseCase(newLine, async = true)
        }
    }

    fun updateLine(updatedLine: Line){
        viewModelScope.launch {
            lineUseCasesContainer.updateLineUseCase(updatedLine, async = true)
        }
    }

    fun deleteBarLineUseCase(barId: Long, lineId: Long){
        viewModelScope.launch {
            lineUseCasesContainer.deleteBarLineUseCase(BarLineIds(barId, lineId))
        }
    }
}