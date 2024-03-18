package com.example.exercisetracker

import org.junit.Assert.*

import org.junit.Test

class TimeCheckTest {

    @Test
    fun checkHoursTest() {
        val isGood = true
        val hours = 24
        /**val mainActivity: MainActivity= MainActivity()**/
        val testTimeCheck: TimeCheck= TimeCheck()
        val test = testTimeCheck.validHours(hours.toString())
        assertEquals(isGood, test)
    }

    @Test
    fun checkMinutesTest() {
        val isGood = true
        val minutes = 60
        /**val mainActivity: MainActivity= MainActivity()**/
        val testTimeCheck: TimeCheck= TimeCheck()
        val test = testTimeCheck.validMinutes(minutes.toString())
        assertEquals(isGood, test)
    }

    @Test
    fun checkSecondsTest() {
        val isGood = true
        val seconds = 60.0
        /**val mainActivity: MainActivity= MainActivity()**/
        val testTimeCheck: TimeCheck= TimeCheck()
        val test = testTimeCheck.validSeconds(seconds.toString())
        assertEquals(isGood, test)
    }

}