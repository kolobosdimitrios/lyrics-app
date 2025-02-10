package com.comp.lyricsapp.presentation.view.home_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun QuickActionBoxGridVertical(quickActions: List<QuickAction>){

    LazyVerticalStaggeredGrid(

        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier.wrapContentSize().padding(15.dp),
        verticalItemSpacing = 8.dp,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {

        items(quickActions) {quickAction ->
            QuickActionBox(
                quickAction = quickAction
            ) //Render info boxes with data
        }
    }

}