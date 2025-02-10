package com.comp.lyricsapp.presentation.view.home_screen

import androidx.compose.runtime.Composable
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
            }
        ),
        QuickAction(
            title = "Quick action 2",
            body = "",
            backgroundColorStart = R.color.colorPrimary,
            backgroundColorEnd = R.color.colorPrimaryDark,
            onClick = {
                // handle on click event
            }
        ),
        QuickAction(
            title = "Quick action 3",
            body = "",
            backgroundColorStart = R.color.colorPrimary,
            backgroundColorEnd = R.color.colorPrimaryDark,
            onClick = {
                // handle on click event
            }
        ),
        QuickAction(
            title = "Quick action 4",
            body = "This is a body for quick action and it is not required.",
            backgroundColorStart = R.color.colorPrimary,
            backgroundColorEnd = R.color.colorPrimaryDark,
            onClick = {
                // handle on click event
            }
        ),
    )

    quickActions = quickActions + quickActions + quickActions + quickActions + quickActions

    //QuickActionBoxGridHorizontal(quickActions= quickActions)
    QuickActionBoxGridVertical(quickActions= quickActions)


}