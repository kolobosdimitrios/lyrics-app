package com.comp.lyricsapp.presentation.screens.project

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.comp.lyricsapp.domain.entities.Bar
import com.comp.lyricsapp.presentation.view_models.BarViewModel

/**
 * All the bars are goind to be rendered inside that board.
 * The workboard
 */
@Composable
fun WorkBoard(
    projectBars: List<Bar>,
    barViewModel: BarViewModel
){

    LazyColumn {
        items(projectBars){ bar ->
            BarPreview(barViewModel,bar)
        }
    }

}

