package com.example.game2048.domain

import com.example.game2048.data.ROWCOUNT
import com.example.game2048.presentation.GameViewModel

class Swipes(private val viewModel: GameViewModel) {

    // собрать все элементы внизу поля
    fun swipeToDown(matrix: MutableList<Int?>): MutableList<Int?> {
        val tempArr = mutableListOf<Int?>()

        for (column in 0 until matrix.size / ROWCOUNT) { // разделили на колонки
            val endIndex = matrix.size - ROWCOUNT + column // конец колонки
            var fullTileQuantity = 0 // кол-во плиток с числом в ряду
            var fullTileCorrection = 0 // кол-во плиток с числом в ряду
            var sumFactor = false // коррекция пустых плиток при суммировании плиток

            for (position in endIndex downTo column step ROWCOUNT) { //проверяем колонку снизу вверх
                var summed = false
                val blockTile = position - ROWCOUNT
                // кол-во сдвигов начиная от начала линии и отнимая кол-во занятых плиток
                val shift =
                    ((position - endIndex) / ROWCOUNT) + fullTileQuantity - fullTileCorrection

                for (comparePosition in position - ROWCOUNT downTo column step ROWCOUNT) {
                    // прерываем если есть между плитками есть другая непустая
                    if (blockSumThroughTile(matrix, comparePosition, blockTile)) break
                    if (matrix[position] == matrix[comparePosition] && matrix[position] != null) {
                        if (summed) {
                            break // если суммирование уже произошло, то прерываем цикл
                        }
                        sumTiles(matrix, position, comparePosition)
                        summed = true
                        sumFactor = true
                    }
                }
                addDigitsToTempArr(tempArr, matrix[position], index = ROWCOUNT * column)
                fullTileQuantity =
                    shiftRegistration(matrix, position, shift, fullTileCorrection, fullTileQuantity)
                // блок для коррекции обнулившейся плитки, после суммироавния
                if (sumFactor) {
                    fullTileCorrection = 1
                    sumFactor = false
                } else fullTileCorrection = 0
            }
            // проверяем длину временного массива и добавляем в его ВЕРХ null,
            // если он короче длины исходного
            while (tempArr.size < (ROWCOUNT * (column + 1))) {
                tempArr.add(
                    index = ROWCOUNT * column,
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

        for (column in 0 until matrix.size / ROWCOUNT) { // разделили на колонки
            val endIndex = matrix.size - ROWCOUNT + column // конец колонки
            var fullTileQuantity = 0 // кол-во плиток с числом в ряду
            var fullTileCorrection = 0 // кол-во плиток с числом в ряду
            var sumFactor = false // коррекция пустых плиток при суммировании плиток

            for (position in column..endIndex step ROWCOUNT) { //проверяем колонку сверху вниз
                var summed = false
                val blockTile = position + ROWCOUNT
                // кол-во сдвигов начиная от начала линии и отнимая кол-во занятых плиток
                val shift = ((position - column) / ROWCOUNT) - fullTileQuantity + fullTileCorrection

                for (comparePosition in position + ROWCOUNT..endIndex step ROWCOUNT) {
                    // прерываем если есть между плитками есть другая непустая
                    if (blockSumThroughTile(matrix, comparePosition, blockTile)) break
                    if (matrix[position] == matrix[comparePosition] && matrix[position] != null) {
                        if (summed) break // если суммирование уже произошло, то прерываем цикл
                        sumTiles(matrix, position, comparePosition)
                        summed = true
                        sumFactor = true
                    }
                }
                addDigitsToTempArr(tempArr, matrix[position], index = tempArr.size)
                fullTileQuantity =
                    shiftRegistration(matrix, position, shift, fullTileCorrection, fullTileQuantity)
                // блок для коррекции обнулившейся плитки, после суммироавния
                if (sumFactor) {
                    fullTileCorrection = 1
                    sumFactor = false
                } else fullTileCorrection = 0
            }
            // проверяем длину временного массива и добавляем в его НИЗ null,
            // если он короче длины исходного
            while (tempArr.size < (ROWCOUNT * (column + 1))) {
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
            var fullTileQuantity = 0 // кол-во плиток с числом в ряду
            var fullTileCorrection = 0 // кол-во плиток с числом в ряду
            var sumFactor = false // коррекция пустых плиток при суммировании плиток

            for (position in endIndex downTo startIndex) { // проверяем справа налево
                var summed = false
                val blockTile = position - 1
                // кол-во сдвигов начиная от начала линии и отнимая кол-во занятых плиток
                val shift = endIndex - position - fullTileQuantity + fullTileCorrection

                for (comparePosition in position - 1 downTo startIndex) {
                    // сравнение соседних элементов линии
                    if (blockSumThroughTile(matrix, comparePosition, blockTile)) {
                        break // прерываем если есть между плитками есть другая непустая
                    }
                    if (matrix[position] == matrix[comparePosition] && matrix[position] != null) {
                        if (summed) break // если суммирование уже произошло, то прерываем цикл
                        sumTiles(matrix, position, comparePosition)
                        summed = true
                        sumFactor = true
                    }
                }
                addDigitsToTempArr(tempArr, matrix[position], index = startIndex)
                fullTileQuantity =
                    shiftRegistration(matrix, position, shift, fullTileCorrection, fullTileQuantity)
                // блок для коррекции обнулившейся плитки, после суммироавния
                if (sumFactor) {
                    fullTileCorrection = 1
                    sumFactor = false
                } else fullTileCorrection = 0
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
            var fullTileCorrection = 0 // кол-во плиток с числом в ряду
            var sumFactor = false // коррекция пустых плиток при суммировании плиток

            for (position in startIndex..endIndex) { // проходим по элементам линии
                var summed = false
                val blockTile = position + 1
                // кол-во сдвигов начиная от начала линии и отнимая кол-во занятых плиток
                val shift = startIndex - position + fullTileQuantity - fullTileCorrection

                // сравнение соседних элементов линии
                for (comparePosition in position + 1..endIndex) {
                    // прерываем если есть между плитками есть другая непустая
                    if (blockSumThroughTile(matrix, comparePosition, blockTile)) break
                    if (matrix[position] == matrix[comparePosition] && matrix[position] != null) {
                        if (summed) break // если суммирование уже произошло, то прерываем цикл
                        sumTiles(matrix, position, comparePosition)
                        summed = true
                        sumFactor = true
                    }
                }
                addDigitsToTempArr(tempArr, matrix[position], index = tempArr.size)
                fullTileQuantity =
                    shiftRegistration(matrix, position, shift, fullTileCorrection, fullTileQuantity)
                // блок для коррекции обнулившейся плитки, после суммироавния
                if (sumFactor) {
                    fullTileCorrection = 1
                    sumFactor = false
                } else fullTileCorrection = 0
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

    private fun shiftRegistration(
        array: MutableList<Int?>,
        position: Int,
        shift: Int,
        fullTileCorrection: Int,
        fullTileQuantity: Int
    ): Int {
        var fullTileQuantityInternal = fullTileQuantity
        val shiftValue = if (fullTileCorrection == 0 && array[position] == null) 0 else shift
        if (array[position] != null) fullTileQuantityInternal++
        viewModel.setTileData(position, shiftValue)
        return fullTileQuantityInternal
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