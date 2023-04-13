package com.example.game2048

data class Matrix(
    private var array: MutableList<Int?> = mutableListOf(
        null, 8192, 4096, 2048,
        1024, null, 256, 128,
        8, 2, null, 64,
        4,2,2,null
    )
) {

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
