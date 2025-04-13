package com.comp.lyricsapp.presentation.screens.project

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.comp.lyricsapp.presentation.view_models.BarViewModel


@Composable
fun BarContainer(
    barViewModel: BarViewModel = hiltViewModel(),
    barIds: Array<Long>
) {
    val barsWithLines by barViewModel.selectedBarLines.collectAsState()

    LaunchedEffect(barIds) {
        barViewModel.getBarsLines(barIds)
    }

    Column(
        modifier = Modifier.padding(5.dp).fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.Start
    ) {

        barsWithLines.forEach { barWithLines ->
            val lines = barWithLines.barLines
            lines.forEach { lineOfBar ->
                LineContainer(
                    lineOfBar,
                    onClick = {
                        Log.d("BarLayout", lineOfBar.toString())
                    }
                )
            }
        }

    }
}



