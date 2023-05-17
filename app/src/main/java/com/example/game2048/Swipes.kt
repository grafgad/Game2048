package com.example.game2048

class Swipes {

    // собрать все элементы внизу поля
    fun swipeToDown(matrix: MutableList<Int?>): MutableList<Int?> {
        val tempArr = mutableListOf<Int?>()

        for (startIndex in 0 until matrix.size / ROWCOUNT) { // разделили на колонки
            val endIndex = matrix.size - ROWCOUNT + startIndex // конец колонки

            for (digit in endIndex downTo startIndex step ROWCOUNT) { //проверяем колонку снизу вверх
                var summed = false

                for (digit2 in digit - ROWCOUNT downTo startIndex step ROWCOUNT) {
                    if (matrix[digit] != null && matrix[digit] == matrix[digit2]) {
                        if (summed) {
                            break // если суммирование уже произошло, то прерываем цикл
                        }
                        summed = true
                        matrix[digit] = matrix[digit]!!.times(2) // удваиваем значение ячейки
                        matrix[digit2] = null // обнуляем соседнюю ячейку
                    }
                }
                // добавляем во временный массив числовые элементы
                if (matrix[digit] != null) {
                    tempArr.add(
                        index = ROWCOUNT * startIndex,
                        element = matrix[digit]
                    )
                }
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

                for (digit2 in digit + ROWCOUNT..endIndex step ROWCOUNT) {
                    if (matrix[digit] != null && matrix[digit] == matrix[digit2]) {
                        if (summed) {
                            break // если суммирование уже произошло, то прерываем цикл
                        }
                        summed = true
                        matrix[digit] = matrix[digit]!!.times(2) // удваиваем значение ячейки
                        matrix[digit2] = null // обнуляем соседнюю ячейку
                    }
                }
                // добавляем во временный массив числовые элементы
                if (matrix[digit] != null) {
                    tempArr.add(matrix[digit])
                }
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

                for (digit2 in digit - 1 downTo startIndex) {
                    // сравнение соседних элементов линии
                    if (matrix[digit] == matrix[digit2] && matrix[digit] != null) {
                        if (summed) {
                            break // если суммирование уже произошло, то прерываем цикл
                        }
                        summed = true
                        matrix[digit] =
                            matrix[digit]?.let { matrix[digit]?.plus(it) } // умножаем значение вдвое
                        matrix[digit2] = null // обнуляем соседнюю ячейку
                    }
                }
                // добавляем во временный массив числовые элементы
                if (matrix[digit] != null) {
                    tempArr.add(
                        index = startIndex,
                        element = matrix[digit]
                    )
                }
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

                for (digit2 in digit + 1..endIndex) {
                    // сравнение соседних элементов линии
                    if (matrix[digit] == matrix[digit2] && matrix[digit] != null) {
                        if (summed) {
                            break // усли суммирование уже произошло, то прерываем цикл
                        }
                        summed = true
                        matrix[digit] = matrix[digit]!!.times(2) // умножаем значение вдвое
                        matrix[digit2] = null // обнуляем соседнюю ячейку
                    }
                }
                // добавляем во временный массив числовые элементы
                if (matrix[digit] != null) {
                    tempArr.add(matrix[digit])
                }
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
}