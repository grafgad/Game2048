package com.example.game2048.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.game2048.GameViewModel
import com.example.game2048.ROWCOUNT
import com.example.game2048.ui.theme.GameColors

@Composable
fun Arena(
    modifier: Modifier = Modifier,
    viewModel: GameViewModel
) {
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    var dir by remember { mutableStateOf(0) }
    Box(
        modifier = modifier
            .padding(16.dp)
            .aspectRatio(1f)
            .background(GameColors.Grey)
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragEnd = {
                        when (dir) {
                            1 -> {
                                viewModel.swipeToRight()
                                Log.d("swipes", "Arena: RIGHT")
                            }
                            2 -> {
                                viewModel.swipeToLeft()
                                Log.d("swipes", "Arena: LEFT")
                            }
                            3 -> {
                                viewModel.swipeToUp()
                                Log.d("swipes", "Arena: UP")
                            }
                            4 -> {
                                viewModel.swipeToDown()
                                Log.d("swipes", "Arena: DOWN")
                            }
                        }
                    },
                    onDrag = { change, dragAmount ->
                        change.consume()
                        val (x, y) = dragAmount
                        when {
                            x > 0 -> { dir = 1 } // RIGHT
                            x < 0 -> { dir = 2 } // LEFT
                        }
                        when {
                            y > 0 -> { dir = 4 } // DOWN
                            y < 0 -> { dir = 3 } // UP
                        }
                        offsetX += dragAmount.x
                        offsetY += dragAmount.y
                    },
                    onDragStart = {},
                    onDragCancel = {}
                )
            }
    ) {
        val vmMatrix by viewModel.matrix.collectAsState()
        Column {
            repeat(ROWCOUNT) { row ->
                HorizontalFields(
                    modifier = Modifier,
                    value = vmMatrix.asMatrix()[row]
                )
            }
        }
    }
}

@Composable
fun HorizontalFields(
    modifier: Modifier = Modifier,
    value: List<Int?> = listOf()
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        Arrangement.SpaceAround
    ) {
        repeat(ROWCOUNT) { digit ->
            Tile(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(3.dp),
                value = value[digit],
            )
        }
    }
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