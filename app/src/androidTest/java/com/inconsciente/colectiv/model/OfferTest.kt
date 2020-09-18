package com.inconsciente.colectiv.model

import org.junit.Assert.*
import org.junit.Test
import java.util.*

internal class OfferTest{
    @Test
    fun offerShouldBeNotStartedWhenCreatedWithDatePlusHour(){
        val plusOneHour = Date(System.currentTimeMillis() + (3600 * 10000))
        val endTime = Date (System.currentTimeMillis() + (360000 *1000))
        val offer = Offer(plusOneHour, endTime)

        assertTrue(offer.millisecondsToStart() > 0)
    }
}