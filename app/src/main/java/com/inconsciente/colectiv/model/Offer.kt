package com.inconsciente.colectiv.model

class Offer(private val duration: Duration) {


    fun millisecondsToStart(): Long {

        return duration.millisecondsToStart()
    }


}