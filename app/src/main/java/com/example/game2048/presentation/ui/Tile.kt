package com.example.game2048.presentation.ui

import androidx.compose.animation.core.animateDpAsState
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
import com.example.game2048.presentation.theme.GameColors
import com.example.game2048.presentation.theme.tileColor
import com.example.game2048.toDp
import kotlinx.coroutines.delay

private const val TEXT_SCALE_REDUCTION_INTERVAL = 0.9f

/*
1 - Определеить для каждой ячейки количество сдвигов для конечного состояния и записать это значение в данные о каждой клетке
2 - На стороне Compose функции Tile проиграть анимацию через offset умножив (количество свдигов) на (ширину клекти)
3 - Получить колбэк об окончании анимации. После окончания анимации нужно заменить сатрые значения клеток на новые значения
 */
@Composable
fun Tile(
    modifier: Modifier = Modifier,
    digit: Int?
) {
    val tileBackgroundColor = GameColors.tileColor(digit)
    var boxSize by remember {
        mutableStateOf(0)
    }
    val boxSizeDp = boxSize.toDp()
    var someOffset by remember {
        mutableStateOf(0.dp)
    }
    val offsetState by animateDpAsState(targetValue = someOffset)
    LaunchedEffect(boxSizeDp) {
        delay(2000)
//        if (digit ==2) someOffset = boxSizeDp
    }
    Box(
        modifier = modifier
            .fillMaxSize()
//            .onSizeChanged {
//                boxSize = it.height
//            }
//            .offset(
//                x = 10.dp,
//                y = offsetState
//            )
            .clip(RoundedCornerShape(5.dp))
            .background(color = tileBackgroundColor),
        contentAlignment = Alignment.Center
    ) {
        ResizedText(text = digit?.toString() ?: "")
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