package com.comp.lyricsapp.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.comp.lyricsapp.domain.entities.Project
import com.comp.lyricsapp.presentation.theme.LightPrimary
import com.comp.lyricsapp.presentation.theme.LightTextSecondary
import com.comp.lyricsapp.presentation.theme.fontFamily

@Composable
fun QuickActionBox(project: Project, onProjectClickListener: (Project) -> Unit){
    Box (
        modifier = Modifier.size(width = 200.dp, height = 150.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        LightPrimary,
                        LightTextSecondary
                    ),
                    startY = 100f
                ),
                shape = RoundedCornerShape(15.dp)
            ).clickable { onProjectClickListener(project) },
        contentAlignment = Alignment.TopStart
    ){

        Column(
            modifier = Modifier
                .padding(8.dp)
            , verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            // Title of the quick action
            Text(
                text = project.title,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Clip,
                fontSize = 16.sp
            )
            //Body text of the quick action if not null

            Text(
                text = project.timeStamp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Clip
            )


        }

    }




}