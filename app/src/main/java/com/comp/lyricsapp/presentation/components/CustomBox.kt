package com.comp.lyricsapp.presentation.components

import android.content.res.Configuration
import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comp.lyricsapp.presentation.theme.LightBackground
import com.comp.lyricsapp.presentation.theme.Shapes

@Composable
fun CustomBox(
    backgroundColor: Color,
    padding: PaddingValues,
    shape: CornerBasedShape = RoundedCornerShape(8.dp),
    contentPadding: PaddingValues = PaddingValues(16.dp),
    content: @Composable (PaddingValues) -> Unit    // <-- This is the key!
){

    Box(modifier = Modifier
        .padding(padding)
        .fillMaxWidth()
        .wrapContentHeight(Alignment.CenterVertically)
        .background(color =backgroundColor, shape = shape)
    ){
        content(contentPadding)
    }
}

@Preview
@Composable
fun CustomBox_Preview(){
    CustomBox(
        backgroundColor = LightBackground,
        padding = PaddingValues(8.dp),
    ) { innerPadding ->

        Text(
            modifier = Modifier.padding(innerPadding),
            text = "Hello World"
        )
    }
}