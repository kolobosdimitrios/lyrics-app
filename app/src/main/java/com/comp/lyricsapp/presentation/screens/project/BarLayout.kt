package com.comp.lyricsapp.presentation.screens.project

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.comp.lyricsapp.presentation.components.CustomBox
import com.comp.lyricsapp.presentation.theme.LightPrimary
import com.comp.lyricsapp.presentation.theme.LightPrimaryLight
import com.comp.lyricsapp.presentation.view_models.BarViewModel


@Composable
fun BarContainer(
    barViewModel: BarViewModel = hiltViewModel(),
    barIds: Array<Long>
) {
    val barsWithLines by barViewModel.selectedBarLines.collectAsState()
    val listState = rememberLazyListState()


    LaunchedEffect(barIds) {
        barViewModel.getBarsLines(barIds)
    }

    // 👇 Scroll to bottom every time barsWithLines changes
    LaunchedEffect(barsWithLines.size) {
        if (barsWithLines.isNotEmpty()) {
            listState.animateScrollToItem(barsWithLines.lastIndex)
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxHeight()
            .padding(8.dp),
        state = listState,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.Start,

    ) {

        items(barsWithLines) { barWithLines ->
            val lines = barWithLines.barLines

            CustomBox(
                backgroundColor = MaterialTheme.colors.primary,
                padding = PaddingValues(0.dp),
            ) { innerPadding ->
                Column(modifier = Modifier.padding(innerPadding)) {
                    if (lines.isEmpty()) {
                        // Placeholder or info message

                    } else {
                        lines.forEach { lineOfBar ->
                            LineContainer(
                                lineOfBar,
                                onClick = { clickedLyric ->
                                    Log.d("BarLayout", clickedLyric.toString())
                                    /**
                                     * Here we can implement the edit.
                                     * When user clicks in a line we can modify that line in the database.
                                     */

                                }
                            )
                        }
                    }
                }
            }

        }

    }
}




