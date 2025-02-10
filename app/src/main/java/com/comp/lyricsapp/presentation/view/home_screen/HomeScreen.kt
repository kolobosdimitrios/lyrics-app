package com.comp.lyricsapp.presentation.view.home_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getString
import androidx.navigation.NavController
import com.comp.lyricsapp.R

@Composable
fun HomeScreen(navController: NavController) {
    val context =
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(getString(LocalContext.current, R.string.app_name))
                    }
                )
            },
            bottomBar = {
                BottomAppBar(
                    contentColor = MaterialTheme.colors.onSecondary,
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp)
                    ) {
                        Text(
                            modifier = Modifier.clickable {

                            },
                            text = "Button 1",
                        )

                        Text(
                            modifier = Modifier.clickable {

                            },
                            text = "Button 2",
                        )
                        Text(
                            modifier = Modifier.clickable {

                            },
                            text = "Button 3",
                        )

                    }
                }
            },
            floatingActionButton = {
                FloatingActionButton(
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = MaterialTheme.colors.onPrimary,
                    onClick = {

                    }
                ) { Icon(Icons.Default.Add, contentDescription = "Add") }
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                DashBoard(navController)
            }
        }

}