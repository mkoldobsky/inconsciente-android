package com.inconsciente.colectiv.model

import java.util.*

class Offer(private val startTime: Date, private val endTime: Date) {


    fun millisecondsToStart(): Long {
        val now = Date()
        return Math.abs(startTime.getTime() - now.getTime())
    }


}