package com.example.game2048.ui.theme

import androidx.compose.ui.graphics.Color


object GameColors {

    val Purple200 = Color(0xFFBB86FC)
    val Purple500 = Color(0xFF6200EE)
    val Purple700 = Color(0xFF3700B3)
    val Teal200 = Color(0xFF03DAC5)
    val Grey = Color(0xFF949494)
    val Yellow = Color(0xFFF8BC25)

    val tileColors = mapOf<Int, Color>(
        2 to Color(0xFFB9F825),
        4 to Color(0xFF2584F8),
        8 to Color(0xFFB9F825),
        16 to Color(0xFFB9F825),
        32 to Color(0xFFB9F825),
        64 to Color(0xFFB9F825),
        128 to Color(0xFFB9F825),
        256 to Color(0xFFB9F825),
        512 to Color(0xFFB9F825),
        1024 to Color(0xFFB9F825),
        2048 to Color(0xFFB9F825),
        4096 to Color(0xFFB9F825),
        8192 to Color(0xFFB9F825),
        16384 to Color(0xFFF825F4),
        32768 to Color(0xFFB9F825),
        65536 to Color(0xFFB9F825),
    )
}

fun GameColors.tileColor(digit: Int?): Color {
    return tileColors[digit] ?: Color(0xFFF825F4)
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
