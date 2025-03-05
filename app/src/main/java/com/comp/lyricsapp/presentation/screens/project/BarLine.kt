package com.comp.lyricsapp.presentation.screens.project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comp.lyricsapp.presentation.theme.DarkPrimary
import com.comp.lyricsapp.presentation.theme.LightPrimary

@Composable
fun BarLine(lyrics: List<String>) {

    Column(
        modifier = Modifier.padding(5.dp).fillMaxWidth().height(450.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.Start
    ) {

        lyrics.forEach{
            lyric -> LyricLine(
                lyric = lyric,
                colors = listOf(
                    LightPrimary, DarkPrimary
                ),
                onClick = {}
            )
        }

    }
}

