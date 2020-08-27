package com.github.eno314.spring.training.infrastructure.repository

import com.github.eno314.spring.training.infrastructure.entity.Race
import org.springframework.data.jpa.repository.JpaRepository

interface RaceRepository : JpaRepository<Race, Long>
