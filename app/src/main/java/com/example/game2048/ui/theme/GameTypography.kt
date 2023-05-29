package com.example.game2048.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
data class GameTypography(
    val h1: TextStyle = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 42.sp,
        color = GameColors.Black
    ),
    val h2: TextStyle = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 32.sp,
        color = GameColors.Black
    ),
    val h3: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        color = GameColors.Black
    ),
    val h4: TextStyle = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        color = GameColors.Black
    ),
    val h5: TextStyle = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        color = GameColors.Black
    ),
    val h6: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        color = GameColors.Black
    ),
    val body1: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = GameColors.Black
    ),
    val body1_medium: TextStyle = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        color = GameColors.Black
    ),
    val body2: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        color = GameColors.Black
    ),
    val body2_medium: TextStyle = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        color = GameColors.Black
    ),
    val subtitle1: TextStyle = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp,
        color = GameColors.Black
    ),
    val subtitle2: TextStyle = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        color = GameColors.Black
    ),
    val subtitle3: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        color = GameColors.Black
    ),
    val subtitle4: TextStyle = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        color = GameColors.Black
    ),
    val subtitle5: TextStyle = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
        color = GameColors.Black
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)