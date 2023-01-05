package com.udacity.asteroidradar

import com.udacity.asteroidradar.base.utils.getTodayDate
import com.udacity.asteroidradar.base.utils.getYesterdayDate
import org.junit.Test

class DateUtilsTest {


    @Test
    fun `test today date`() {
        val todayDate = getTodayDate()
        assert(todayDate.equals("2023-01-05"))
    }

    @Test
    fun `test yesterday date`() {
        val todayDate = getYesterdayDate()
        assert(!todayDate.equals("2023-01-05"))
        assert(todayDate.equals("2023-01-04"))
    }
}