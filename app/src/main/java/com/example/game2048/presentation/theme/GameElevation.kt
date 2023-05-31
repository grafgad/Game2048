package com.example.game2048.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp

@Immutable
data class GameElevation(
    val default: Dp,
    val pressed: Dp
)