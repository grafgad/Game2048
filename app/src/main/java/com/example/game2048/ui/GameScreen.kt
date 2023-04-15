package com.example.game2048.ui

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Statistics(
            modifier = Modifier,
            movesCount = movesCount
        )
        Buttons(
            modifier = Modifier,
            viewModel = viewModel
        )
        Arena(viewModel = viewModel)
        SwipeButtons(modifier, viewModel)
    }
}

@Composable
fun Statistics(
    modifier: Modifier = Modifier,
    movesCount: Int,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = (stringResource(id = R.string.moves) + movesCount),
            modifier = Modifier.padding(2.dp),
            fontSize = 20.sp,
        )
        Text(text = "Scores")
    }
}

@Composable
fun Buttons(
    modifier: Modifier = Modifier,
    viewModel: GameViewModel
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { /*TODO Cancel last move*/ },
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.baseline_undo_24),
                contentDescription = "Undo"
            )
        }
        Button(
            {
                Swipes().newGame()
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
    viewModel: GameViewModel
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { Swipes().swipeToUp() }) {
            Text(text = "UP")
        }
        Row {
            Button(
                onClick = { Swipes().swipeToLeft() },
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                Text(text = "LEFT")
            }
            Button(
                onClick = {
                    viewModel.onClick()
                    Log.d("DDDDDD", viewModel.matrix.value.asMatrix().toString())
                },
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                Text(text = "RIGHT")
            }
        }
        Button(onClick = {
            Swipes().swipeToDown()
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