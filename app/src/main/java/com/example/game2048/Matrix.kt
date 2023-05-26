package com.example.game2048

const val ROWCOUNT = 4

data class Matrix(
    var array: MutableList<Int?> = arrayOfNulls<Int?>(16).toMutableList()
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
