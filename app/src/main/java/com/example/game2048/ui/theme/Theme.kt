package com.example.game2048.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette =
    with(GameColors) {
        darkColors(
            primary = Primary,
            primaryVariant = Purple700,
            secondary = Teal200,
            background = Background,
        )
    }

private val LightColorPalette = lightColors(
    primary = GameColors.Purple500,
    primaryVariant = GameColors.Purple700,
    secondary = GameColors.Teal200,
    background = GameColors.Background,

    /* Other default colors to override
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun Game2048Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (darkTheme) DarkColorPalette else LightColorPalette,
        typography = GameTypography,
        shapes = GameShapes,
        content = content
    )
}



