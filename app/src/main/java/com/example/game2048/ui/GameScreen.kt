package com.example.game2048.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.game2048.GameViewModel
import com.example.game2048.R
import com.example.game2048.Swipes

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    movesCount: Int = 0,
    viewModel: GameViewModel = viewModel()
) {
    val moves = remember {
        mutableStateOf(movesCount)
    }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Statistics(
            modifier = Modifier,
            movesCount = moves
        )
        Buttons(
            modifier = Modifier,
            viewModel = viewModel,
            movesCount = moves
        )
        Arena(viewModel = viewModel)
        SwipeButtons(
            modifier,
            viewModel,
            movesCount = moves
        )
    }
}

@Composable
fun Statistics(
    modifier: Modifier = Modifier,
    movesCount: MutableState<Int>,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = (stringResource(id = R.string.moves) + movesCount.value.toString()),
            modifier = Modifier.padding(2.dp),
            fontSize = 20.sp,
        )
        Text(text = "Scores")
    }
}

@Composable
fun Buttons(
    modifier: Modifier = Modifier,
    viewModel: GameViewModel,
    movesCount: MutableState<Int>,
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = {
                movesCount.value--
            },
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.baseline_undo_24),
                contentDescription = "Undo"
            )
        }
        Button(
            {
                viewModel.newGame()
            }
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.baseline_autorenew_24),
                contentDescription = "Start new game",
                modifier = Modifier
                    .padding(end = 10.dp)
                    .rotate(90f)
            )
            Text(text = stringResource(id = R.string.start_new_game))
        }
    }
}

@Composable
fun SwipeButtons(
    modifier: Modifier = Modifier,
    viewModel: GameViewModel,
    movesCount: MutableState<Int>,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            movesCount.value++
            viewModel.swipeToUp()
            Log.d("DDDDDD", "up ${viewModel.matrix.value.asMatrix().toString()}")
        }) {
            Text(text = "UP")
        }
        Row {
            Button(
                onClick = {
                    movesCount.value++
                    viewModel.swipeToLeft()
                    Log.d("DDDDDD", "left ${viewModel.matrix.value.asMatrix().toString()}")
                },
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                Text(text = "LEFT")
            }
            Button(
                onClick = {
                    movesCount.value++
                    viewModel.swipeToRight()
                    Log.d("DDDDDD", "right ${viewModel.matrix.value.asMatrix().toString()}")
                },
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                Text(text = "RIGHT")
            }
        }
        Button(onClick = {
            movesCount.value++
            viewModel.swipeToDown()
            Log.d("DDDDDD", "down ${viewModel.matrix.value.asMatrix().toString()}")
        }) {
            Text(text = "DOWN")
        }
    }

}

@Preview
@Composable
fun GameScreenPreview() {
    GameScreen()
}