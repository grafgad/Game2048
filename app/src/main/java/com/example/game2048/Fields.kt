package com.example.game2048

object Fields {

     var line1: Array<Int?> = arrayOf(16384, 8192, 4096, 2048)
    private var line2: Array<Int?> = arrayOf(1024, 512, 256, 128)
    private var line3: Array<Int?> = arrayOf(8, 16, 32, 64)
    private var line4: Array<Int?> = arrayOf(4,2,null,8)

    var matrix: Array<Array<Int?>> = arrayOf(line1, line2, line3, line4)
}