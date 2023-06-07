package com.example.game2048.data

const val ROWCOUNT = 4

data class Matrix(
//    var array: MutableList<Int?> = arrayOfNulls<Int?>(ROWCOUNT * ROWCOUNT).toMutableList()
    var array: MutableList<Int?> = arrayOf(
        2, 2, 2, 4,
        128,null, 64,16,
        512,1024, null, 2048,
        32768,null,8192,null,
    ).toMutableList()
) {
    fun matrixCopy(newArray: MutableList<Int?>): Matrix = Matrix().copy(
        array = newArray.toMutableList()
    )

    fun squareMatrix(): MutableList<List<Int?>> {
        return array.chunked(ROWCOUNT).toMutableList()
    }
}