package com.example.game2048.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberSwipeableState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.game2048.Fields
import com.example.game2048.GameViewModel
import com.example.game2048.ui.theme.GameColors
import kotlin.math.roundToInt

enum class States {
    RIGHT,
    LEFT,
    UP,
    DOWN
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Arena(
    modifier: Modifier = Modifier,
    viewModel: GameViewModel
) {
    val swipeableState = rememberSwipeableState(initialValue = 0)
    val direction = 0f/*TODO: переделать для направления*/

    Box(
        modifier = modifier
            .padding(16.dp)
            .aspectRatio(1f)
            .background(GameColors.Grey)
//            .swipeable(
//                state = swipeableState,
//                anchors = mapOf(
//                    0f to 0,
//                    direction to 1
//                ),/*TODO: доделать*/
//                orientation = Orientation.Horizontal,/*TODO: сделать для 4-х направлений*/
//            )
    ) {
        val matrix = viewModel.matrix.collectAsState().value
        Column {


            for (i in 0 until 4) {
                HorizontalFields(
                    value = matrix.smth[i]
//                    value = Fields.emptyMatrix.smth[i]
                )
            }
        }
    }
}

@Composable
fun HorizontalFields(
    modifier: Modifier = Modifier,
    value: MutableList<Int?> = mutableListOf()
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        Arrangement.SpaceAround
    ) {
        for (i in 0 until 4) {
            Tile(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(3.dp)
                    .background(GameColors.Yellow),
                value = value[i],
                textStyle = TextStyle(fontSize = 50.sp),
            )
        }
    }
}



@Preview
@Composable
fun HorizontalFieldsPreview() {
    HorizontalFields(
        modifier = Modifier,
        Fields.line1
    )
}
@Preview(widthDp = 200, heightDp = 320)
@Composable
fun ArenaPreview320() {
    Arena(viewModel = GameViewModel())
}

@Preview
@Composable
fun ArenaPReview() {
    Arena(viewModel = GameViewModel())
}