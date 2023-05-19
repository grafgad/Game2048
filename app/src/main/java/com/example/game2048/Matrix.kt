package com.example.game2048

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

const val ROWCOUNT = 4

data class Matrix(
    var array: MutableList<Int?> = arrayOfNulls<Int?>(16).toMutableList(),
    var moves: MutableState<Int> = mutableStateOf(0),
    var scores: MutableState<Int> = mutableStateOf(0)
) {
    fun matrixCopy(newArray: MutableList<Int?>): Matrix = Matrix().copy(
        array = newArray.toMutableList()
    )

    fun asMatrix(): MutableList<List<Int?>> {
        val outerList = mutableListOf<List<Int?>>()
        repeat(ROWCOUNT) { row ->
            val innerList = mutableListOf<Int?>()
            repeat(array.size / ROWCOUNT) { i ->
                innerList.add(i, array[row * ROWCOUNT + i])
            }
            outerList.add(innerList)
        }
        return outerList
    }
}
