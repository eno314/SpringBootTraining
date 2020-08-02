package com.github.eno314.spring.training.infrastructure.repository

import com.github.eno314.spring.training.infrastructure.entity.Jockey
import org.springframework.data.jpa.repository.JpaRepository

interface JockeyRepository : JpaRepository<Jockey, String>
