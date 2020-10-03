package com.github.eno314.spring.training.infrastructure.entity

import java.time.LocalDate

interface DailyJockeyResult {

    fun getDate(): LocalDate

    fun getBestResult(): Int

    fun getJockeyId(): String
}
