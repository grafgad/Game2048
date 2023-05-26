package com.example.game2048.ui.theme

import androidx.compose.ui.graphics.Color


object GameColors {

    val Purple200 = Color(0xFFBB86FC)
    val Primary = Color(0xFFEBDBFF)
    val Purple500 = Color(0xFF6200EE)
    val Purple700 = Color(0xFF3700B3)
    val Teal200 = Color(0xFF03DAC5)
    val Grey = Color(0xFF949494)
    val Background = Color(0xC8BBB9B9)
    val Yellow = Color(0xFFF8BC25)
    val EmptyTileColor = Color(0xFF616161)


    val tileColors = mapOf<Int, Color>(
        2 to Color(0xCDD3D3D3),
        4 to Color(0xEBB6B5B5),
        8 to Color(0xEBA2A2A2),
        16 to Color(0xFFD8FFC9),
        32 to Color(0xFFB3FC97),
        64 to Color(0xFF8FFD65),
        128 to Color(0xFFB4FD8C),
        256 to Color(0xFF94FF5A),
        512 to Color(0xFF7FFD3B),
        1024 to Color(0xFF77FFB4),
        2048 to Color(0xFF38E9FA),
        4096 to Color(0xFF255D6B),
        8192 to Color(0xFF3BA5EB),
        16384 to Color(0xFF8536F5),
        32768 to Color(0xFF7C35EE),
        65536 to Color(0xFF8F25F8),
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
