package com.inconsciente.colectiv.model

import java.util.*
import kotlin.math.abs

class Duration(private val start: Date, private val end:Date){
    fun millisecondsToStart(): Long {
        val now = Date()
        return abs(start.time - now.time)
    }
    fun millisecondsToEnd(): Long{
        val now = Date()
        return abs(end.time - now.time)
    }
    fun millisecondsDuration():Long{
        return abs(end.time - start.time)
    }
}