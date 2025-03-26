package com.comp.lyricsapp.presentation.screens.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.comp.lyricsapp.domain.entities.Bar
import com.comp.lyricsapp.domain.entities.Line
import com.comp.lyricsapp.presentation.theme.LightPrimary
import com.comp.lyricsapp.presentation.view_models.BarViewModel

@Composable
fun BarPreview(barViewModel: BarViewModel, bar: Bar) {
    BarDisplay(barViewModel, bar, backgroundColor = LightPrimary)
}

@Composable
private fun BarDisplay(
    barViewModel: BarViewModel,
    bar: Bar,
    backgroundColor: Color
) {
    val lines = barViewModel.selectedBarLines.collectAsState()

    LaunchedEffect(bar.id) {
        barViewModel.getBarLines(bar.id)
    }

    BarLine(
        lines.value,
        modifier = Modifier.background(backgroundColor)
    )
}

@Composable
private fun BarLine(lines: List<Line>, modifier: Modifier) {
    LazyColumn(
        modifier = modifier.padding(5.dp).fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.Start
    ) {
        items(lines){ line ->
            LyricLine(
                line,
                onClick = {}
            )
        }

    }
}
