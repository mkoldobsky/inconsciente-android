package com.inconsciente.colectiv.model

import org.junit.Assert.*
import org.junit.Test
import java.util.*

internal class DurationTest{
    @Test
    fun durationShouldBeNotStartedWhenCreatedWithDatePlusHour(){
        val plusOneHour = Date(System.currentTimeMillis() + (3600 * 10000))
        val endTime = Date (System.currentTimeMillis() + (360000 *1000))
        val duration = Duration(plusOneHour, endTime)

        assertTrue(duration.millisecondsToStart() > 0)
    }
    @Test
    fun durationShouldBeNotFinishWhenFinishPlusHour(){
        val plusOneHour = Date(System.currentTimeMillis() + (3600 * 10000))

        val duration = Duration(Date(), plusOneHour)
        assertTrue(duration.millisecondsToEnd() > 0)
    }
    @Test
    fun durationInMillisecondsShouldBeOneHour(){
        val plusOneHour = Date(System.currentTimeMillis() + (3600 * 10000))
        val duration = Duration(Date(), plusOneHour)
        assertEquals(duration.millisecondsDuration(), 36000000L)
    }
}