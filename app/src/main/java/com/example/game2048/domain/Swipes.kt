package com.example.game2048.domain

import com.example.game2048.data.ROWCOUNT
import com.example.game2048.presentation.GameViewModel

class Swipes(private val viewModel: GameViewModel) {

    // собрать все элементы внизу поля
    fun swipeToDown(matrix: MutableList<Int?>): MutableList<Int?> {
        val tempArr = mutableListOf<Int?>()

        for (startIndex in 0 until matrix.size / ROWCOUNT) { // разделили на колонки
            val endIndex = matrix.size - ROWCOUNT + startIndex // конец колонки

            for (position in endIndex downTo startIndex step ROWCOUNT) { //проверяем колонку снизу вверх
                var summed = false
                val blockTile = position - ROWCOUNT

                for (comparePosition in position - ROWCOUNT downTo startIndex step ROWCOUNT) {

                    // прерываем если есть между плитками есть другая непустая
                    if (blockSumThroughTile(matrix, comparePosition, blockTile)) break

                    if (matrix[position] == matrix[comparePosition] && matrix[position] != null) {
                        if (summed) {
                            break // если суммирование уже произошло, то прерываем цикл
                        }
                        sumTiles(matrix, position, comparePosition)
                        summed = true
                    }
                }
                addDigitsToTempArr(tempArr, matrix[position], index = ROWCOUNT * startIndex)
            }
            // проверяем длину временного массива и добавляем в его ВЕРХ null,
            // если он короче длины исходного
            while (tempArr.size < (ROWCOUNT * (startIndex + 1))) {
                tempArr.add(
                    index = ROWCOUNT * startIndex,
                    element = null
                )
            }
        }
        //записываем в старый массив новые значения в правильном порядке
        repeat(times = ROWCOUNT) { i ->
            repeat(times = ROWCOUNT) { k ->
                matrix[i * ROWCOUNT + k] = tempArr[ROWCOUNT * k + i]
            }
        }
        return matrix
    }

    // собрать все элементы вверху поля
    fun swipeToUp(matrix: MutableList<Int?>): MutableList<Int?> {
        val tempArr = mutableListOf<Int?>()

        for (startIndex in 0 until matrix.size / ROWCOUNT) { // разделили на колонки
            val endIndex = matrix.size - ROWCOUNT + startIndex // конец колонки

            for (position in startIndex..endIndex step ROWCOUNT) { //проверяем колонку сверху вниз
                var summed = false
                val blockTile = position + ROWCOUNT
                for (comparePosition in position + ROWCOUNT..endIndex step ROWCOUNT) {
                    // прерываем если есть между плитками есть другая непустая
                    if (blockSumThroughTile(matrix, comparePosition, blockTile)) break

                    if (matrix[position] == matrix[comparePosition] && matrix[position] != null) {
                        if (summed) break // если суммирование уже произошло, то прерываем цикл
                        sumTiles(matrix, position, comparePosition)
                        summed = true
                    }
                }
                addDigitsToTempArr(tempArr, matrix[position], index = tempArr.size)
            }
            // проверяем длину временного массива и добавляем в его НИЗ null,
            // если он короче длины исходного
            while (tempArr.size < (ROWCOUNT * (startIndex + 1))) {
                tempArr.add(null)
            }
        }
        //записываем в старый массив новые значения в правильном порядке
        repeat(times = ROWCOUNT) { i ->
            repeat(times = ROWCOUNT) { k ->
                matrix[i * ROWCOUNT + k] = tempArr[ROWCOUNT * k + i]
            }
        }
        return matrix
    }


    // собрать элементы с цифрами в конце
    fun swipeToRight(matrix: MutableList<Int?>): MutableList<Int?> {
        val tempArr = mutableListOf<Int?>()

        for (line in 0 until matrix.size / ROWCOUNT) { // разделили на линии
            val startIndex = line * ROWCOUNT // начало линии
            val endIndex = startIndex + ROWCOUNT - 1 // конец линии

            for (position in endIndex downTo startIndex) { // проверяем справа налево
                var summed = false
                val blockTile = position - 1

                for (comparePosition in position - 1 downTo startIndex) {
                    // сравнение соседних элементов линии
                    if (blockSumThroughTile(matrix, comparePosition, blockTile)) {
                        break // прерываем если есть между плитками есть другая непустая
                    }
                    if (matrix[position] == matrix[comparePosition] && matrix[position] != null) {
                        if (summed) break // если суммирование уже произошло, то прерываем цикл
                        sumTiles(matrix, position, comparePosition)
                        summed = true
                    }
                }
                addDigitsToTempArr(tempArr, matrix[position], index = startIndex)
            }
            // проверяем длину временного массива и добавляем в его НАЧАЛО null,
            // если он короче длины исходного
            while (tempArr.size <= endIndex) {
                tempArr.add(
                    index = startIndex,
                    element = null
                )
            }
        }
        //записываем в старый массив новые значения
        repeat(times = tempArr.size) { position ->
            matrix[position] = tempArr[position]
        }
        return matrix
    }


    // собрать элементы с цифрами в начале
    fun swipeToLeft(matrix: MutableList<Int?>): MutableList<Int?> {
        val tempArr = mutableListOf<Int?>()

        for (line in 0 until matrix.size / ROWCOUNT) { // разделили на линии
            val startIndex = line * ROWCOUNT // начало линии
            val endIndex = startIndex + ROWCOUNT - 1 // конец линии
            var fullTileQuantity = 0 // кол-во плиток с числом в ряду
            var emptrTileQuantity = 0

            for (position in startIndex..endIndex) { // проходим по элементам линии
                var summed = false
                val blockTile = position + 1
                // кол-во сдвигов начиная от начала линии и отнимая кол-во занятых плиток

                val shift = (position - startIndex) - fullTileQuantity
                viewModel.setTileData(position, shift)

                for (comparePosition in position + 1..endIndex) { // сравнение соседних элементов линии
                    // прерываем если есть между плитками есть другая непустая
                    if (blockSumThroughTile(matrix, comparePosition, blockTile)) break
                    if (matrix[position] == matrix[comparePosition] && matrix[position] != null) {
                        if (summed) break // если суммирование уже произошло, то прерываем цикл
                        sumTiles(matrix, position, comparePosition)
                        summed = true
                    }
                }
                addDigitsToTempArr(tempArr, matrix[position], index = tempArr.size)

                if (summed) fullTileQuantity--
                if (matrix[position] != null) {
                    fullTileQuantity++
                }

            }
            // проверяем длину временного массива и добавляем в его КОНЕЦ null,
            // если он короче длины исходного
            while (tempArr.size <= endIndex) tempArr.add(null)
        }
        //записываем в старый массив новые значения
        repeat(times = tempArr.size) { position ->
            matrix[position] = tempArr[position]
        }
        return matrix
    }

    private fun tileShiftReg(
        array: MutableList<Int?>,
        position: Int,
        shift: Int,
        fullTile: Int
    ): Int {
        var fullTileQuantity = fullTile
        if (array.contains(null) || array.size != array.toSet().size) {
            viewModel.setTileData(
                position,
                shift
            )
            if (array[position] != null) {
                fullTileQuantity++
            }
        }
        return fullTileQuantity
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

    // добавляем во временный массив числовые элементы
    private fun addDigitsToTempArr(array: MutableList<Int?>, digit: Int?, index: Int) {
        if (digit != null) {
            array.add(
                index = index,
                element = digit
            )
        }
    }
}