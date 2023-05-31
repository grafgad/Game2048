package com.example.game2048.presentation.theme

import androidx.compose.ui.graphics.Color


object GameColors {

    val Purple200 = Color(0xFFBB86FC)
    val Primary = Color(0xFF8BC34A)
    val Purple500 = Color(0xFF6200EE)
    val Purple700 = Color(0xFF3700B3)
    val Teal200 = Color(0xFF03DAC5)
    val Grey = Color(0xFF949494)
    val Background = Color(0xC8BBB9B9)
    val Yellow = Color(0xFFF8BC25)
    val EmptyTileColor = Color(0xFF616161)
    val Black = Color(0xFF000000)
    val Transparent = Color(0x00000000)


    val tileColors = mapOf<Int, Color>(
        2 to Color(0xEBA2A2A2),
        4 to Color(0xEBB6B5B5),
        8 to Color(0xCDD3D3D3),
        16 to Color(0xFFD8FFC9),
        32 to Color(0xFFB3FC97),
        64 to Color(0xFF8FFD65),
        128 to Color(0xFF88E952),
        256 to Color(0xFF80EB44),
        512 to Color(0xFF27A729),
        1024 to Color(0xFF3C61E9),
        2048 to Color(0xFF371ADA),
        4096 to Color(0xFF7B5999),
        8192 to Color(0xFF843ED5),
        16384 to Color(0xFFF58F36),
        32768 to Color(0xFFEE8B35),
        65536 to Color(0xFFF8A425),
    )
}

fun GameColors.tileColor(digit: Int?): Color {
    return tileColors[digit] ?: EmptyTileColor
}

/*
primary: Color,
primaryVariant: Color,
secondary: Color,
secondaryVariant: Color,
background: Color,
surface: Color,
error: Color,
onPrimary: Color,
onSecondary: Color,
onBackground: Color,
onSurface: Color,
onError: Color
*/
