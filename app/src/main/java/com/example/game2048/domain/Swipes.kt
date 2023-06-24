package com.example.game2048.domain

import com.example.game2048.ROWCOUNT
import com.example.game2048.data.TileData
import com.example.game2048.presentation.GameViewModel

class Swipes(private val viewModel: GameViewModel) {

    fun swipeToDown(matrix: MutableList<TileData>): MutableList<TileData> {
        val tempArr = mutableListOf<TileData>()

        for (column in 0 until matrix.size / ROWCOUNT) {
            val endIndex = matrix.size - ROWCOUNT + column // конец колонки
            var fullTileQuantity = 0
            var summedTileFactor = 0

            for (position in endIndex downTo column step ROWCOUNT) {
                var summed = false
                val blockTile = position - ROWCOUNT
                val tileShift =
                    ((position - endIndex) / ROWCOUNT) + fullTileQuantity - summedTileFactor
                summed = sumDigits(
                    position, matrix, blockTile, summed,
                    range = ((position - ROWCOUNT) downTo column step ROWCOUNT)
                )
                addDigitsToTempArr(tempArr, matrix[position], index = ROWCOUNT * column)
                shiftRegistration(matrix, position, tileShift, summedTileFactor)
                fullTileQuantity = fullTileCalc(fullTileQuantity, matrix, position)
                summedTileFactor = checkFullTileCorrection(summed)
            }
            addNullsToTempArrVertical(tempArr, (ROWCOUNT * (column + 1)), (ROWCOUNT * column))
        }
        return matrixUpdateVertical(matrix, tempArr)
    }

    fun swipeToUp(matrix: MutableList<TileData>): MutableList<TileData> {
        val tempArr = mutableListOf<TileData>()

        for (column in 0 until matrix.size / ROWCOUNT) {
            val endIndex = matrix.size - ROWCOUNT + column // конец колонки
            var fullTileQuantity = 0
            var summedTileFactor = 0

            for (position in column..endIndex step ROWCOUNT) {
                var summed = false
                val blockTile = position + ROWCOUNT
                val tileShift =
                    ((position - column) / ROWCOUNT) - fullTileQuantity + summedTileFactor
                summed = sumDigits(
                    position, matrix, blockTile, summed,
                    range = position + ROWCOUNT..endIndex step ROWCOUNT
                )
                addDigitsToTempArr(tempArr, matrix[position], index = tempArr.size)
                shiftRegistration(matrix, position, tileShift, summedTileFactor)
                fullTileQuantity = fullTileCalc(fullTileQuantity, matrix, position)
                summedTileFactor = checkFullTileCorrection(summed)
            }
            addNullsToTempArrVertical(tempArr, (ROWCOUNT * (column + 1)), null)
        }
        return matrixUpdateVertical(matrix, tempArr)
    }

    fun swipeToRight(matrix: MutableList<TileData>): MutableList<TileData> {
        val tempArr = mutableListOf<TileData>()

        for (line in 0 until matrix.size / ROWCOUNT) {
            val startIndex = line * ROWCOUNT //
            val endIndex = startIndex + ROWCOUNT - 1
            var fullTileQuantity = 0
            var summedTileFactor = 0

            for (position in endIndex downTo startIndex) {
                var summed = false
                val blockTile = position - 1
                val tileShift = endIndex - position - fullTileQuantity + summedTileFactor
                summed = sumDigits(
                    position, matrix, blockTile, summed,
                    range = position - 1 downTo startIndex
                )
                addDigitsToTempArr(tempArr, matrix[position], index = startIndex)
                shiftRegistration(matrix, position, tileShift, summedTileFactor)
                fullTileQuantity = fullTileCalc(fullTileQuantity, matrix, position)
                summedTileFactor = checkFullTileCorrection(summed)
            }
            addNullsToTempArrHorizontal(tempArr, endIndex, startIndex)
        }
        return matrixUpdateHorizontal(matrix, tempArr)
    }

    fun swipeToLeft(matrix: MutableList<TileData>): MutableList<TileData> {
        val tempArr = mutableListOf<TileData>()

        for (line in 0 until matrix.size / ROWCOUNT) {
            val startIndex = line * ROWCOUNT
            val endIndex = startIndex + ROWCOUNT - 1
            var fullTileQuantity = 0
            var summedTileFactor = 0

            for (position in startIndex..endIndex) {
                var summed = false
                val blockTile = position + 1
                val tileShift = startIndex - position + fullTileQuantity - summedTileFactor
                summed = sumDigits(
                    position, matrix, blockTile, summed,
                    range = position + 1..endIndex
                )
                addDigitsToTempArr(tempArr, matrix[position], index = tempArr.size)
                shiftRegistration(matrix, position, tileShift, summedTileFactor)
                fullTileQuantity = fullTileCalc(fullTileQuantity, matrix, position)
                summedTileFactor = checkFullTileCorrection(summed)
            }
            addNullsToTempArrHorizontal(tempArr, endIndex, null)
        }
        return matrixUpdateHorizontal(matrix, tempArr)
    }

    private fun sumDigits(
        position: Int,
        matrix: MutableList<TileData>,
        blockTile: Int,
        summed: Boolean,
        range: IntProgression,
    ): Boolean {
        for (comparePosition in range) {
            if (blockSumThroughFullTile(matrix, comparePosition, blockTile)) break
            if (matrix[position].digit == matrix[comparePosition].digit && matrix[position].digit != null) {
                if (summed) break // если суммирование уже произошло, то прерываем цикл
                sumTiles(matrix, position, comparePosition)
                return true
            }
        }
        return false
    }

    private fun blockSumThroughFullTile( // прерываем если между плитками есть другая непустая плитка
        array: MutableList<TileData>,
        comparePosition: Int,
        blockTile: Int
    ): Boolean {
        if (array[blockTile].digit != null && comparePosition != blockTile) {
            return true
        }
        return false
    }

    private fun sumTiles(
        matrix: MutableList<TileData>,
        position: Int,
        comparePosition: Int
    ) {
        matrix[position].digit =
            matrix[position].digit!!.times(2)  // умножаем значение плитки вдвое
        matrix[comparePosition].digit = null // обнуляем соседнюю ячейку
        viewModel.setMoveScore(matrix[position].digit!!)
    }

    private fun addDigitsToTempArr(array: MutableList<TileData>, digit: TileData, index: Int) {
        if (digit.digit != null) {
            array.add(
                index = index,
                element = digit
            )
        }
    }

    private fun shiftRegistration(
        // регистрация сдвига плитки для анимации
        array: MutableList<TileData>,
        position: Int,
        shift: Int,
        fullTileCorrection: Int,
    ) {
        val shiftValue = if (fullTileCorrection == 0 && array[position].digit == null) 0 else shift
        viewModel.setTileShift(position, array[position].digit, shiftValue)
    }

    private fun fullTileCalc(
        fullTileQuantity: Int,
        array: MutableList<TileData>,
        position: Int,
    ): Int {
        var fullTileQuantityInternal = fullTileQuantity
        if (array[position].digit != null) fullTileQuantityInternal++
        return fullTileQuantityInternal
    }

    private fun checkFullTileCorrection(sumFactor: Boolean): Int {
        return if (sumFactor) 1 else 0
    }

    private fun addNullsToTempArrVertical(
        tempArr: MutableList<TileData>,
        lineSize: Int,
        index: Int?
    ) {
        while (tempArr.size < lineSize) {
            if (index != null) tempArr.add(index = index, element = TileData(null, 0))
            else tempArr.add(element = TileData(null, 0))
        }
    }

    private fun addNullsToTempArrHorizontal(
        tempArr: MutableList<TileData>,
        lineSize: Int,
        index: Int?
    ) {
        while (tempArr.size <= lineSize) {
            if (index != null) tempArr.add(index = index, element = TileData(null, 0))
            else tempArr.add(element = TileData(null, 0))
        }
    }

    private fun matrixUpdateVertical(
        matrix: MutableList<TileData>,
        tempArr: MutableList<TileData>
    ): MutableList<TileData> {
        repeat(times = ROWCOUNT) { i ->
            repeat(times = ROWCOUNT) { k ->
                matrix[i * ROWCOUNT + k] =
                    TileData(tempArr[ROWCOUNT * k + i].digit, tempArr[ROWCOUNT * k + i].shift)
            }
        }
        return matrix
    }

    private fun matrixUpdateHorizontal(
        matrix: MutableList<TileData>,
        tempArr: MutableList<TileData>
    ): MutableList<TileData> {
        repeat(times = tempArr.size) { position ->
            matrix[position] = TileData(tempArr[position].digit, tempArr[position].shift)
        }
        return matrix
    }
}