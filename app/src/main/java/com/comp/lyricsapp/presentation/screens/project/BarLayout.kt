package com.comp.lyricsapp.presentation.screens.project

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
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
    var barColor = 1


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

            val backgroundColor = when (barColor) {
                1 -> LightPrimary
                else -> LightPrimaryLight
            }

            barColor = 1 - barColor // Toggle between 0 and 1


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = backgroundColor
                    )
            ){

                Column {
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

    }
}




