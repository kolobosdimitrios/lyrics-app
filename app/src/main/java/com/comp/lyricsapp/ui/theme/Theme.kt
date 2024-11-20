package com.comp.lyricsapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Light Theme Color Palette
val LightColorPalette = lightColors(
    primary = LightPrimary,
    primaryVariant = LightPrimaryDark,
    secondary = LightAccent,
    background = LightBackground,
    surface = LightSurface,
    error = LightError,
    onPrimary = LightTextPrimary,
    onSecondary = LightTextPrimary,
    onBackground = LightTextPrimary,
    onSurface = LightTextPrimary,
    onError = Color.White // white text on error color
)

// Dark Theme Color Palette
val DarkColorPalette = darkColors(
    primary = DarkPrimary,
    primaryVariant = DarkPrimaryDark,
    secondary = DarkAccent,
    background = DarkBackground,
    surface = DarkSurface,
    error = DarkError,
    onPrimary = DarkTextPrimary,
    onSecondary = DarkTextPrimary,
    onBackground = DarkTextPrimary,
    onSurface = DarkTextPrimary,
    onError = Color.Black // black text on error color
)

@Composable
fun MyAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}