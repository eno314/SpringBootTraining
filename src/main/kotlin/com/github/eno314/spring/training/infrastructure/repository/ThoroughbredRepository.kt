package com.github.eno314.spring.training.infrastructure.repository

import com.github.eno314.spring.training.infrastructure.entity.Thoroughbred
import org.springframework.data.jpa.repository.JpaRepository

interface ThoroughbredRepository : JpaRepository<Thoroughbred, String>
