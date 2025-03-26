package com.comp.lyricsapp.presentation.screens.project

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.comp.lyricsapp.domain.entities.Line
import com.comp.lyricsapp.presentation.theme.Typography

/**
 * lyric: The text of the lyric.
 * color: The color of the layout.
 */
@Composable
fun LyricLine(lyric: Line, onClick: () -> Unit){

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable(onClick = onClick, enabled = true)

    ) {
        Text(
            text = lyric.line,
            Modifier
                .wrapContentSize(align = Alignment.Center)
                .padding(5.dp),
            style = Typography.body2
        )
    }

}

