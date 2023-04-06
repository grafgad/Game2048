package com.example.game2048

import kotlin.concurrent.timerTask
import kotlin.math.absoluteValue

class Swipes {
    fun swipeToDown() {

        for (line in 0 until 4) {
            var lowestEmptyTile = Fields.matrix.size - 1
            // считаем снизу вверх
            for (digit in 3 downTo 0) {
                val tile = Fields.matrix[digit][line]
                if (tile != null) {
                    Fields.matrix[lowestEmptyTile][line] = tile
                    Fields.matrix[digit][line] = null
                }
                lowestEmptyTile = digit
            }
        }
    }

    fun swipeToRight() {

        for (line in 0 until 4) {
//         считаем справа налево

            var mostRightEmptyTile = Fields.matrix[line].size - 1
            for (digit in 3 downTo 0) {
                val tile = Fields.matrix[line][digit]
                if (tile != null) {
//                    Fields.matrix[line][mostRightEmptyTile-1] = null
                    Fields.matrix[line][digit] = tile
                }
                if (tile == null) {
                    Fields.matrix[line].reverse(digit, mostRightEmptyTile)
                }



//                else {
////                    mostRightEmptyTile = digit
//
//                    Fields.matrix[line].reverse(digit, mostRightEmptyTile)
//
//
//                }
                mostRightEmptyTile = digit
            }


            //            for (digit in 3 downTo 0) {
//                val tile = Fields.matrix[line][digit]
//                if (tile != null) {
//                    Fields.matrix[line][mostRightEmptyTile] = tile
//                    Fields.matrix[line][digit] = null
//                    mostRightEmptyTile = digit
//                }
//            }
        }
    }
}