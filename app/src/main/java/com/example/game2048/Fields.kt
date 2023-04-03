package com.example.game2048

class Fields {

    private val line1: Array<Int?> = arrayOf(16384, 8192, 4096, 2048)
    private val line2: Array<Int?> = arrayOf(1024, 512, 256, 128)
    private val line3: Array<Int?> = arrayOf(8, 16, 32, 64)
    private val line4: Array<Int?> = arrayOf(4,2,null,8)

    val vertLine: Array<Array<Int?>> = arrayOf(line1, line2, line3, line4)
}