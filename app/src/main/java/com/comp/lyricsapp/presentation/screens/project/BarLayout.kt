package com.comp.lyricsapp.presentation.screens.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.comp.lyricsapp.domain.usecases.ProjectBarIds
import com.comp.lyricsapp.presentation.components.SwipeToDeleteBox
import com.comp.lyricsapp.presentation.view_models.BarViewModel
import com.comp.lyricsapp.presentation.view_models.LineViewModel


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

            SwipeToDeleteBox(
                onDelete = {
                    barViewModel.deleteProjectBar(
                        ProjectBarIds(
                            projectId = barWithLines.bar.projectId,
                            barId = barWithLines.bar.id
                        )
                    )
                }
            ) {

                Column(
                    modifier = Modifier.background(MaterialTheme.colors.primary, shape = RoundedCornerShape(4.dp))
                ) {
                    if (lines.isEmpty()) {
                        androidx.compose.material.Text(
                            "No lines yet! Add one to get started.",
                            modifier = Modifier.padding(8.dp)
                        )
                    } else {
                        lines.forEach { lineOfBar ->
                            LineContainer(
                                lineOfBar,
                                onClick = { clickedLyric -> /* ... */ }
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(8.dp))

        }
    }

}




