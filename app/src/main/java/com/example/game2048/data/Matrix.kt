package com.example.game2048.data

import com.example.game2048.ROWCOUNT

data class Matrix(
    var array: MutableList<TileData> = arrayOf(
        TileData(2, 0),
        TileData(2, 0),
        TileData(2, 0),
        TileData(4, 0),
        TileData(128, 0),
        TileData(null, 0),
        TileData(64, 0),
        TileData(4, 0),
        TileData(512, 0),
        TileData(1024, 0),
        TileData(null, 0),
        TileData(2048, 0),
        TileData(32768, 0),
        TileData(null, 0),
        TileData(8192, 0),
        TileData(null, 0),
    ).toMutableList()
) {
    fun matrixCopy(newArray: MutableList<TileData>): Matrix = Matrix().copy(
        array = newArray.toMutableList()
    )

    fun squareMatrix(): MutableList<List<TileData>> {
        return array.chunked(ROWCOUNT).toMutableList()
    }
}