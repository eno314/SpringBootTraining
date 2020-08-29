package com.github.eno314.spring.training.domain.dto

data class RaceResultFindParams(
    val raceId: Long?,
    val thoroughbredId: String?,
    val jockeyId: String?,
)
