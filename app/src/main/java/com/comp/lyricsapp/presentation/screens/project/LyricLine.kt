package com.comp.lyricsapp.presentation.screens.project

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
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
fun LyricLine(lyric: String, colors: List<Color>, onClick: () -> Unit){

    val gradientBrush = Brush.horizontalGradient(colors)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(brush = gradientBrush, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
            .clickable(onClick = onClick, enabled = true)

    ) {
        Text(
            text = lyric,
            Modifier
                .wrapContentSize(align = Alignment.Center)
                .padding(15.dp),
            style = Typography.body2
        )
    }

}

