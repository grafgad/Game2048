package com.example.game2048.domain

import com.example.game2048.data.ROWCOUNT
import com.example.game2048.presentation.GameViewModel

class Swipes(private val viewModel: GameViewModel) {

    fun swipeToDown(matrix: MutableList<Int?>): MutableList<Int?> {
        val tempArr = mutableListOf<Int?>()

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

    fun swipeToUp(matrix: MutableList<Int?>): MutableList<Int?> {
        val tempArr = mutableListOf<Int?>()

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

    fun swipeToRight(matrix: MutableList<Int?>): MutableList<Int?> {
        val tempArr = mutableListOf<Int?>()

        for (line in 0 until matrix.size / ROWCOUNT) {
            val startIndex = line * ROWCOUNT // начало линии
            val endIndex = startIndex + ROWCOUNT - 1 // конец линии
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

    fun swipeToLeft(matrix: MutableList<Int?>): MutableList<Int?> {
        val tempArr = mutableListOf<Int?>()

        for (line in 0 until matrix.size / ROWCOUNT) {
            val startIndex = line * ROWCOUNT // начало линии
            val endIndex = startIndex + ROWCOUNT - 1 // конец линии
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
        matrix: MutableList<Int?>,
        blockTile: Int,
        summed: Boolean,
        range: IntProgression,
    ): Boolean {
        for (comparePosition in range) {
            // прерываем если есть между плитками есть другая непустая
            if (blockSumThroughTile(matrix, comparePosition, blockTile)) break
            if (matrix[position] == matrix[comparePosition] && matrix[position] != null) {
                if (summed) break // если суммирование уже произошло, то прерываем цикл
                sumTiles(matrix, position, comparePosition)
                return true
            }
        }
        return false
    }

    private fun blockSumThroughTile(
        array: MutableList<Int?>,
        comparePosition: Int,
        blockTile: Int
    ): Boolean {
        if (array[blockTile] != null && comparePosition != blockTile) {
            return true
        }
        return false
    }

    private fun sumTiles(
        matrix: MutableList<Int?>,
        position: Int,
        comparePosition: Int
    ) {
        matrix[position] = matrix[position]!!.times(2) // умножаем значение вдвое
        matrix[comparePosition] = null // обнуляем соседнюю ячейку
        viewModel.setMoveScore(matrix[position]!!)
    }

    private fun addNullsToTempArrVertical(
        tempArr: MutableList<Int?>,
        lineSize: Int,
        index: Int?
    ) {
        while (tempArr.size < lineSize) {
            if (index != null) {
                tempArr.add(index = index, element = null)
            } else {
                tempArr.add(element = null)
            }
        }
    }

    private fun addNullsToTempArrHorizontal(
        tempArr: MutableList<Int?>,
        lineSize: Int,
        index: Int?
    ) {
        while (tempArr.size <= lineSize) {
            if (index != null) {
                tempArr.add(
                    index = index,
                    element = null
                )
            } else {
                tempArr.add(
                    element = null
                )
            }
        }
    }

    // добавляем во временный массив числовые элементы
    private fun addDigitsToTempArr(array: MutableList<Int?>, digit: Int?, index: Int) {
        if (digit != null) {
            array.add(
                index = index,
                element = digit
            )
        }
    }

    private fun matrixUpdateVertical(
        matrix: MutableList<Int?>,
        tempArr: MutableList<Int?>
    ): MutableList<Int?> {
        repeat(times = ROWCOUNT) { i ->
            repeat(times = ROWCOUNT) { k ->
                matrix[i * ROWCOUNT + k] = tempArr[ROWCOUNT * k + i]
            }
        }
        return matrix
    }

    private fun matrixUpdateHorizontal(
        matrix: MutableList<Int?>,
        tempArr: MutableList<Int?>
    ): MutableList<Int?> {
        repeat(times = tempArr.size) { position ->
            matrix[position] = tempArr[position]
        }
        return matrix
    }

    private fun shiftRegistration(
        array: MutableList<Int?>,
        position: Int,
        shift: Int,
        fullTileCorrection: Int,
    ) {
        val shiftValue = if (fullTileCorrection == 0 && array[position] == null) 0 else shift
        viewModel.setTileData(position, shiftValue)
    }

    private fun fullTileCalc(
        fullTileQuantity: Int,
        array: MutableList<Int?>,
        position: Int,
    ): Int {
        var fullTileQuantityInternal = fullTileQuantity
        if (array[position] != null) fullTileQuantityInternal++
        return fullTileQuantityInternal
    }

    private fun checkFullTileCorrection(sumFactor: Boolean): Int {
        return if (sumFactor) 1 else 0
    }
}