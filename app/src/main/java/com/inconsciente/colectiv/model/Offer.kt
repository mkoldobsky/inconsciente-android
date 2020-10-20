package com.inconsciente.colectiv.model

class Offer(private val duration: Duration, private val name:String, private val description:String, private val qty:Int, val imageUrl:String) {


    fun millisecondsToStart(): Long {

        return duration.millisecondsToStart()
    }

    fun representName(): String {
        return name
    }

    fun representDescription(): String {
        return description
    }


}