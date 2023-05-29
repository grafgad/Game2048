package com.example.game2048.ui.theme

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.LocalContentColor
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Immutable
data class Game2048Colors( //переименовать для удобства
    val primary: Color,
    val primaryVariant: Color,
    val secondary: Color,
    val background: Color,
    val content: Color,
)

val LocalColors = staticCompositionLocalOf {
    Game2048Colors(
        primary = Color.Unspecified,
        primaryVariant = Color.Unspecified,
        secondary = Color.Unspecified,
        background = Color.Unspecified,
        content = Color.Unspecified,
    )
}

val LocalTypography = staticCompositionLocalOf {
    GameTypography()
}

val LocalElevation = staticCompositionLocalOf {
    GameElevation(
        default = 4.dp,
        pressed = 8.dp
    )
}

@Immutable
private object GameRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor(): Color =
        RippleTheme.defaultRippleColor(LocalContentColor.current, lightTheme = true)

    @Composable
    override fun rippleAlpha() = DefaultRippleAlfa
}

private val DefaultRippleAlfa = RippleAlpha(
    pressedAlpha = 0.12f,
    focusedAlpha = 0.12f,
    draggedAlpha = 0.16f,
    hoveredAlpha = 0.08f
)

private val DarkColorPalette =
    with(GameColors) {
        Game2048Colors(
            primary = Purple500,
            primaryVariant = Purple700,
            secondary = Teal200,
            background = Background,
            content = Grey,
        )
    }

private val LightColorPalette = Game2048Colors(
    primary = GameColors.Purple500,
    primaryVariant = GameColors.Purple700,
    secondary = GameColors.Teal200,
    background = GameColors.Background,
    content = GameColors.Grey,

    /* Other default colors to override
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

object GameTheme {
    val color: Game2048Colors
        @Composable
        get() = LocalColors.current

    val typography: GameTypography
        @Composable
        get() = LocalTypography.current

    val elevation: GameElevation
        @Composable
        get() = LocalElevation.current
}

@Composable
fun Game2048Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val gameColors = if (darkTheme) DarkColorPalette else LightColorPalette

    CompositionLocalProvider(
        LocalColors provides gameColors,
        LocalContentColor provides GameTheme.color.content,
        LocalElevation provides GameTheme.elevation,
        LocalIndication provides rememberRipple(),
        LocalRippleTheme provides GameRippleTheme,
        LocalTypography provides GameTheme.typography,
        content = content
    )
}
