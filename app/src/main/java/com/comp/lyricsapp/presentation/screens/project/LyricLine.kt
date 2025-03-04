package com.comp.lyricsapp.presentation.screens.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comp.lyricsapp.presentation.theme.DarkPrimaryDark
import com.comp.lyricsapp.presentation.theme.LightPrimaryDark
import com.comp.lyricsapp.presentation.theme.Shapes
import com.comp.lyricsapp.presentation.theme.Typography

/**
 * lyric: The text of the lyric.
 * color: The color of the layout.
 */
@Composable
fun LyricLine(lyric: String, colors: List<Color>){

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(shape = Shapes.medium, brush = Brush.horizontalGradient(
                colors
            ), alpha = 0.85f)
    ) {
        Text(text = lyric, Modifier.padding(15.dp), style = Typography.body2)
    }

}

@Preview
@Composable
fun PreviewTiles(){

    LyricLine(
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas porttitor auctor placerat. In facilisis leo at quam facilisis, ut finibus.",
        listOf(
            DarkPrimaryDark, LightPrimaryDark
        )
    )
}