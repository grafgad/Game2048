package com.example.game2048.ui

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
import com.example.game2048.Fields
import com.example.game2048.R
import com.example.game2048.Swipes

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    movesCount: Int = 0
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { /*TODO Cancel last move*/ },
//                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.baseline_undo_24),
                    contentDescription = "Undo"
                )
            }
//            Spacer(modifier = Modifier.weight(1f))
            Button(
//                modifier = Modifier.weight(1f),
                onClick = {
                    println(Fields.matrix[2].contentToString())
                    println(Fields.matrix[3].contentToString())
                    Swipes().swipeToRight()
                    println("swipe")
                    println(Fields.matrix[2].contentToString())
                    println(Fields.matrix[3].contentToString())
//                    println(Fields.matrix[0][2])
//                    println(Fields.matrix[1][2])
//                    println(Fields.matrix[2][2])
//                    println(Fields.matrix[3][2])
//                    println("swipe")
//                    Swipes().swipeToDown()
//                    println(Fields.matrix[0][2])
//                    println(Fields.matrix[1][2])
//                    println(Fields.matrix[2][2])
//                    println(Fields.matrix[3][2])


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
        Arena()
    }
}

@Preview
@Composable
fun GameScreenPreview() {
    GameScreen()
}