package com.example.game2048.presentation.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.game2048.TEXT_SCALE_REDUCTION_INTERVAL
import com.example.game2048.TILEPADDING
import com.example.game2048.data.Directions
import com.example.game2048.data.TileData
import com.example.game2048.presentation.theme.GameColors
import com.example.game2048.presentation.theme.tileColor
import com.example.game2048.toDp


/*
1 - Определеить для каждой ячейки количество сдвигов для конечного состояния и записать это значение в данные о каждой клетке
2 - На стороне Compose функции Tile проиграть анимацию через offset умножив (количество свдигов) на (ширину клекти)
3 - Получить колбэк об окончании анимации. После окончания анимации нужно заменить сатрые значения клеток на новые значения
 */

enum class AnimationState {
    START,
    END
}

@Composable
fun Tile(
    modifier: Modifier = Modifier,
    tileData: TileData,
    direction: Directions
) {
    val tileBackgroundColor = GameColors.tileColor(tileData.digit)
    var boxSize by remember { mutableStateOf(0) }
    val shift = tileData.shift.times(boxSize.plus((TILEPADDING * 2)))
    val boxSizeDp = boxSize.toDp()
    var moveOffset by remember { mutableStateOf(shift.dp) }
    val horizontalOffset by animateDpAsState(
        targetValue = when (direction) {
            Directions.RIGHT -> moveOffset
            Directions.LEFT -> moveOffset
            else -> 0.dp
        },
        animationSpec = tween(durationMillis = 500)
    )
    val verticalOffset by animateDpAsState(
        targetValue = when (direction) {
            Directions.UP -> moveOffset
            Directions.DOWN -> moveOffset
            else -> 0.dp
        },
        animationSpec = tween(durationMillis = 500)
    )
    LaunchedEffect(shift) {
        moveOffset = shift.dp
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .onSizeChanged {
                boxSize = it.height
            }
            .offset( // vertical
                x = 0.dp,
                y = verticalOffset
            )
            .offset( // horizontal
                x = horizontalOffset,
                y = 0.dp
            )

            .clip(RoundedCornerShape(5.dp))
            .background(color = tileBackgroundColor),
        contentAlignment = Alignment.Center
    ) {
        ResizedText(text = tileData.digit?.toString() ?: "")
    }
}

@Composable
fun ResizedText(
    text: String,
    style: TextStyle = TextStyle(fontSize = 42.sp)
) {
    var resizedTextStyle by remember(text) { mutableStateOf(style) }

    Text(
        text = text,
        modifier = Modifier.padding(2.dp),
        softWrap = false,
        fontSize = resizedTextStyle.fontSize,
        onTextLayout = { textLayoutResult ->
            if (textLayoutResult.didOverflowWidth) {
                resizedTextStyle = resizedTextStyle.copy(
                    fontSize = resizedTextStyle.fontSize.times(TEXT_SCALE_REDUCTION_INTERVAL)
                )
            }
        }
    )
}