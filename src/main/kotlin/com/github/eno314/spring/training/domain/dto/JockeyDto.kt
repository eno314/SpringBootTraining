package com.github.eno314.spring.training.domain.dto

import com.github.pozo.KotlinBuilder
import java.time.LocalDateTime

@KotlinBuilder
data class JockeyDto(
    val id: String,
    val name: String,
    val updatedAt: LocalDateTime
)
