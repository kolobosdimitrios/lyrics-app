package com.comp.lyricsapp.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.comp.lyricsapp.R

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

val fontFamily = FontFamily(
    Font( R.font.comic_neue_regular, FontWeight.Normal),
    Font( R.font.comic_neue_italic, FontWeight.Normal),
    Font( R.font.comic_neue_light, FontWeight.Light),
    Font( R.font.comic_neue_light_italic, FontWeight.Light),
    Font( R.font.comic_neue_bold, FontWeight.Bold),
    Font( R.font.comic_neue_bold_italic, FontWeight.Bold)
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