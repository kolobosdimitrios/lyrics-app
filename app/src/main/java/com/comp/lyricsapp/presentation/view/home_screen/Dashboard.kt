package com.comp.lyricsapp.presentation.view.home_screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.comp.lyricsapp.R
import com.comp.lyricsapp.presentation.navigation.Screen

@Composable
fun DashBoard(navController: NavController){
    var quickActions = listOf /*Quick Actions*/(
        QuickAction(
            title = "Quick action 1",
            body = "This is a body for quick action and it is not required.",
            backgroundColorStart = R.color.colorPrimary,
            backgroundColorEnd = R.color.colorPrimaryDark,
            onClick = {
                // handle on click event
                navController.navigate(route = Screen.ProjectsList.route)
            }
        ),
        QuickAction(
            title = "Quick action 2",
            body = "",
            backgroundColorStart = R.color.colorPrimary,
            backgroundColorEnd = R.color.colorPrimaryDark,
            onClick = {
                // handle on click event
                navController.navigate(route = Screen.ProjectsList.route)
            }
        ),
        QuickAction(
            title = "Quick action 3",
            body = "",
            backgroundColorStart = R.color.colorPrimary,
            backgroundColorEnd = R.color.colorPrimaryDark,
            onClick = {
                // handle on click event
                navController.navigate(route = Screen.ProjectsList.route)
            }
        ),
        QuickAction(
            title = "Quick action 4",
            body = "This is a body for quick action and it is not required.",
            backgroundColorStart = R.color.colorPrimary,
            backgroundColorEnd = R.color.colorPrimaryDark,
            onClick = {
                // handle on click event
                navController.navigate(route = Screen.ProjectsList.route)
            }
        ),
    )

    quickActions = quickActions + quickActions + quickActions + quickActions + quickActions

    //QuickActionBoxGridHorizontal(quickActions= quickActions)
    QuickActionBoxGridVertical(quickActions= quickActions)


}