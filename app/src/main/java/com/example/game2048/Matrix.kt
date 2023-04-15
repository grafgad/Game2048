package com.example.game2048

const val ROWCOUNT = 4

data class Matrix(
     var array: MutableList<Int?> = mutableListOf(
//        null, null, null, null,
//        null, null, null, null,
//        null, null, null, null,
//        null, null, null, null

        null, 8192, 4096, 2048,
        1024, null, 256, 128,
        8, 2, null, 64,
        4,2,2,null
    )
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

    operator fun set(index: Int, value: Int?) {
        array[index] = value
    }
}
