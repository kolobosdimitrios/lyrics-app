package com.comp.lyricsapp.presentation.screens.project

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.comp.lyricsapp.domain.entities.Line
import com.comp.lyricsapp.presentation.theme.Typography
import com.comp.lyricsapp.presentation.view_models.LineViewModel

/**
 * lyric: The text of the lyric.
 * color: The color of the layout.
 */
@Composable
fun LineContainer(
    lineViewModel: LineViewModel = hiltViewModel(),
    line: Line,
    onClick: (lyric: Line) -> Unit
){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onClick(line) }, enabled = true),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Text(
            text = line.line,
            modifier = Modifier.weight(1f).padding(PaddingValues(start = 16.dp)),
            style = Typography.body2
        )

        IconButton(
            onClick = { lineViewModel.deleteLine(line) }
        ) {
            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = "Delete Line",
                modifier = Modifier.size(16.dp)
            )
        }
    }

}




