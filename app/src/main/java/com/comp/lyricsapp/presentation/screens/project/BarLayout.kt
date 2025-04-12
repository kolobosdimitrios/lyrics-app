package com.comp.lyricsapp.presentation.screens.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.comp.lyricsapp.domain.entities.Bar
import com.comp.lyricsapp.domain.entities.Line
import com.comp.lyricsapp.presentation.theme.LightPrimary
import com.comp.lyricsapp.presentation.theme.LightPrimaryLight
import com.comp.lyricsapp.presentation.theme.Typography
import com.comp.lyricsapp.presentation.view_models.BarViewModel
import com.comp.lyricsapp.presentation.view_models.LineViewModel
import java.util.stream.Stream

@Composable
fun StandaloneBar(barViewModel: BarViewModel, lineViewModel: LineViewModel) {

    var standaloneValues by remember { mutableStateOf(listOf<String>()) }
    var newStandaloneBar by remember { mutableStateOf("") }


    val focusManager = LocalFocusManager.current  // Manages focus
    val keyboardController = LocalSoftwareKeyboardController.current  // Manages keyboard
    val focusRequester = remember { FocusRequester() } // Focus controller

    Box(
        modifier = Modifier.fillMaxWidth().padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp).fillMaxWidth().wrapContentHeight(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(standaloneValues){
                        value -> Text(value)
                }
            }

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester)
                ,
                value = newStandaloneBar,
                onValueChange = {
                    newValue -> newStandaloneBar = newValue
                },
                placeholder = { Text("Hit it", style = Typography.body2, color = Color.White) },
                singleLine = false,
                textStyle = Typography.body2,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = LightPrimary,
                    textColor = Color.White,
                    cursorColor = Color.White,
                    focusedIndicatorColor = LightPrimary,  // 👈 Removes line when focused
                    unfocusedIndicatorColor = Color.Transparent // 👈 Removes line when unfocused

                ),
                keyboardActions = KeyboardActions(onDone = {
                    standaloneValues += newStandaloneBar// Save text
                    focusManager.clearFocus()  // Remove focus from TextField
                    keyboardController?.hide()  // Hide keyboard
                    newStandaloneBar = ""
                }),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            focusManager.clearFocus()  // Move focus to TextField
                            keyboardController?.hide()

                        }
                    ) {
                        Icon(imageVector = Icons.Default.Save, contentDescription = "Save lines", tint = Color.White)
                    }
                }
            )
        }

    }

}

@Composable
fun BarPreview(barViewModel: BarViewModel, bar: Bar) {
    BarDisplay(barViewModel, bar, backgroundColor = LightPrimary)
}

@Composable
private fun BarDisplay(
    barViewModel: BarViewModel,
    bar: Bar,
    backgroundColor: Color
) {
    val lines = barViewModel.selectedBarLines.collectAsState()

    LaunchedEffect(bar.id) {
        barViewModel.getBarLines(bar.id)
    }

    BarLine(
        lines.value,
        modifier = Modifier.background(backgroundColor)
    )
}

@Composable
private fun BarLine(lines: List<Line>, modifier: Modifier) {
    LazyColumn(
        modifier = modifier.padding(5.dp).fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.Start
    ) {
        items(lines){ line ->
            LyricLine(
                line,
                onClick = {}
            )
        }

    }
}
