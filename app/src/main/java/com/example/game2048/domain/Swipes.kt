package com.example.game2048.domain

import com.example.game2048.data.ROWCOUNT
import com.example.game2048.presentation.GameViewModel

class Swipes(private val viewModel: GameViewModel) {

    // собрать все элементы внизу поля
    fun swipeToDown(matrix: MutableList<Int?>): MutableList<Int?> {
        val tempArr = mutableListOf<Int?>()

        for (startIndex in 0 until matrix.size / ROWCOUNT) { // разделили на колонки
            val endIndex = matrix.size - ROWCOUNT + startIndex // конец колонки

            for (digit in endIndex downTo startIndex step ROWCOUNT) { //проверяем колонку снизу вверх
                var summed = false
                val blockTile = digit - ROWCOUNT

                for (digit2 in digit - ROWCOUNT downTo startIndex step ROWCOUNT) {
                    if (matrix[blockTile] != null && matrix[digit2] != matrix[blockTile]){
                        break // прерываем если есть между плитками есть другая непустая
                    }
                    if (matrix[digit] == matrix[digit2] && matrix[digit] != null) {
                        if (summed) {
                            break // если суммирование уже произошло, то прерываем цикл
                        }
                        sumTiles(matrix, digit, digit2)
                        summed = true
                    }
                }
                addDigitsToTempArr(tempArr, matrix[digit], index = ROWCOUNT * startIndex)
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

            for (digit in startIndex..endIndex step ROWCOUNT) { //проверяем колонку сверху вниз
                var summed = false
                val blockTile = digit + ROWCOUNT
                for (digit2 in digit + ROWCOUNT..endIndex step ROWCOUNT) {
                    if (matrix[blockTile] != null && matrix[digit2] != matrix[blockTile]){
                        break // прерываем если есть между плитками есть другая непустая
                    }
                    if (matrix[digit] == matrix[digit2] && matrix[digit] != null) {
                        if (summed) {
                            break // если суммирование уже произошло, то прерываем цикл
                        }
                        sumTiles(matrix, digit, digit2)
                        summed = true
                    }
                }
                addDigitsToTempArr(tempArr, matrix[digit], index = tempArr.size)
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

            for (digit in endIndex downTo startIndex) { // проверяем справа налево
                var summed = false
                val blockTile = digit - 1

                for (digit2 in digit - 1 downTo startIndex) {
                    // сравнение соседних элементов линии
                    if (matrix[blockTile] != null && matrix[digit2] != matrix[blockTile]){
                        break // прерываем если есть между плитками есть другая непустая
                    }
                    if (matrix[digit] == matrix[digit2] && matrix[digit] != null) {
                        if (summed) {
                            break // если суммирование уже произошло, то прерываем цикл
                        }
                        sumTiles(matrix, digit, digit2)
                        summed = true
                    }
                }
                addDigitsToTempArr(tempArr, matrix[digit], index = startIndex)
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

            for (digit in startIndex..endIndex) { // проходим по элементам линии
                var summed = false
                val blockTile = digit+1

                for (digit2 in digit + 1..endIndex) { // сравнение соседних элементов линии
                    if (matrix[blockTile] != null && matrix[digit2] != matrix[blockTile]){
                        break // прерываем если есть между плитками есть другая непустая
                    }
                    if (matrix[digit] == matrix[digit2] && matrix[digit] != null) {
                        if (summed) {
                            break // если суммирование уже произошло, то прерываем цикл
                        }
                        sumTiles(matrix, digit, digit2)
                        summed = true
                    }
                }
                addDigitsToTempArr(tempArr, matrix[digit], index = tempArr.size)
            }
            // проверяем длину временного массива и добавляем в его КОНЕЦ null,
            // если он короче длины исходного
            while (tempArr.size <= endIndex) {
                tempArr.add(null)
            }
        }
        //записываем в старый массив новые значения
        repeat(times = tempArr.size) { position ->
            matrix[position] = tempArr[position]
        }
        return matrix
    }

    private fun sumTiles(
        matrix: MutableList<Int?>,
        digit: Int,
        digit2: Int
    ) {
        matrix[digit] = matrix[digit]!!.times(2) // умножаем значение вдвое
        matrix[digit2] = null // обнуляем соседнюю ячейку
        viewModel.setMoveScore(matrix[digit]!!)
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