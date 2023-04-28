package com.example.game2048

import android.util.Log

class Swipes {
    fun newGame() {

    }

    fun swipeToUp() {}
    var line = mutableListOf( null, null, 1, 2,)

    fun swipeToLeft() {
        //собрать элементы с цифрами в начале
        if (line.contains(null)) {
            for (element in line.indices) {
                if (line.first() == null) {
                    line.add(null)
                    line.removeAt(0)
                    println(line)
                }

//                if (line[element] == null) {
//                    line.add(0, 0)
//                    line.removeAt(
//                        index = element
//                    )
//                }
//                println(line)
            }
        }


//        for (i in line.size-1 downTo 0) {
//            if (line[i] == null && i>0) {
//                line[i] = line[i-1]
//                line[i-1] = null
//                Log.d("DDDDDD", line.toString())
//            }
//
//        }

//        repeat(line.size) {position ->
//            if (line[position] != null) {
//                line[position]?.let { line.last()?.plus(it) }
//                println(line[position])
//                println(position)
//            }
//        }

    }
    fun swipeToDown() {

//        for (line in 3 downTo 0) {
//            var lowestEmptyTile: Int? = Fields.matrix.size - 1
//            var lowestFullTile: Int? = null
//            for (digit in 0 until 4) {
//                val tile = Fields.matrix[line][digit]
//                if (tile != null && line != 0) {
//                    lowestFullTile = Fields.matrix[line-1][digit]
//                    if (lowestFullTile != null) {
//                        Collections.swap(Fields.matrix[line], digit, lowestFullTile)
//                    }
//                }
//            }
//        }

//        for (line in 0 until 4) {
//            var lowestEmptyTile = Fields.matrix.size - 1
//            // считаем снизу вверх
//            for (digit in 3 downTo 0) {
//                val tile = Fields.matrix[digit][line]
//                if (tile != null) {
//                    Fields.matrix[lowestEmptyTile][line] = tile
//                    Fields.matrix[digit][line] = null
//                }
//                lowestEmptyTile = digit
//            }
//        }
    }

    fun swipeToRight() {
        //собрать элементы с цифрами в конце
        if (line.contains(null)) {
            for (element in line.size - 1 downTo 0) {
                if (line.last() == null) {
                    line.add(0, null)
                    line.removeAt(line.size - 1)
                    println(line)
                }
            }
        }
    }
}