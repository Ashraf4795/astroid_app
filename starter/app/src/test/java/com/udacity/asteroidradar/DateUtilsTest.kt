package com.udacity.asteroidradar

import com.udacity.asteroidradar.base.utils.getTodayDate
import org.junit.Test

class DateUtilsTest {


    @Test
    fun `test today date`() {
        val todayDate = getTodayDate()
        assert(todayDate.equals("2023-01-04"))
    }
}