package com.example.game2048

class Fields {

    private val line1 = arrayOf(16384, 8192, 4096, 2048)
    private val line2 = arrayOf(1024, 512, 256, 128)
    private val line3 = arrayOf(8, 16, 32, 64)
    private val line4 = arrayOf(4,2,2,8)

    val vertLine: Array<Array<Int>> = arrayOf(line1, line2, line3, line4)
}