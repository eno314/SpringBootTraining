package com.github.eno314.spring.training.infrastructure.repository

import com.github.eno314.spring.training.infrastructure.entity.RaceResult
import org.springframework.data.jpa.repository.JpaRepository

interface RaceResultRepository : JpaRepository<RaceResult, Long>
