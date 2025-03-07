package com.comp.lyricsapp.presentation.screens.project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.comp.lyricsapp.presentation.theme.Typography

@Composable
fun ProjectScreen(navController: NavController){

    var projectTitle by remember { mutableStateOf("") }
    var savedProjectTitle by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current  // Manages focus
    val keyboardController = LocalSoftwareKeyboardController.current  // Manages keyboard
    val focusRequester = remember { FocusRequester() } // Focus controller

    Scaffold (
        topBar = {
            TopAppBar(
                title = {

                    TextField(
                        modifier = Modifier.fillMaxWidth().focusRequester(focusRequester),
                        value = projectTitle,
                        onValueChange = { newValue -> projectTitle = newValue},
                        placeholder = { Text("Project Title", style = Typography.h1) },
                        singleLine = true,
                        textStyle = Typography.h1,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done  // Set Done button on keyboard
                        ),
                        trailingIcon = {

                            Row(){
                                IconButton(
                                    onClick = {
                                        focusRequester.requestFocus()  // Move focus to TextField
                                        keyboardController?.show()
                                    }
                                ) {
                                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Title")
                                }

                                IconButton(
                                    onClick = {
                                        //Save project!!
                                    }
                                ) {
                                    Icon(imageVector = Icons.Default.Save, contentDescription = "Save Title")
                                }
                            }

                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            cursorColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,  // 👈 Removes line when focused
                            unfocusedIndicatorColor = Color.Transparent  // 👈 Removes line when unfocused

                        ),
                        keyboardActions = KeyboardActions(onDone = {
                            savedProjectTitle = projectTitle  // Save text
                            focusManager.clearFocus()  // Remove focus from TextField
                            keyboardController?.hide()  // Hide keyboard
                        })
                    )

                }
            )
        }
    ){ innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            Text(
                text = "The name of the project will be ${savedProjectTitle}!"
            )
        }
    }
}


