package com.example.game2048

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Composable
internal fun Dp.toPx(): Float {
    return LocalDensity.current.run { this@toPx.toPx() }
}

@Composable
internal fun Int.toDp(): Dp {
    return LocalDensity.current.run { this@toDp.toDp() }
}