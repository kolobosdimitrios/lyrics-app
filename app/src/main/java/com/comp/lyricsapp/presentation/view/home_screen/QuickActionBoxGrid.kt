package com.comp.lyricsapp.presentation.view.home_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun QuickActionBoxGrid(quickActions: List<QuickAction>){

    LazyHorizontalStaggeredGrid(
        rows = StaggeredGridCells.Fixed(2),
        modifier = Modifier.wrapContentSize(),
        horizontalItemSpacing = 8.dp,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        items(quickActions) {quickAction ->
            QuickActionBox(
               quickAction = quickAction
            ) //Render info boxes with data
        }
    }

}