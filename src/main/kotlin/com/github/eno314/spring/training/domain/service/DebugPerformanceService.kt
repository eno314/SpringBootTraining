package com.github.eno314.spring.training.domain.service

import com.github.eno314.spring.training.infrastructure.entity.Jockey
import com.github.eno314.spring.training.infrastructure.repository.JockeyRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import javax.transaction.Transactional

@Service
class DebugPerformanceService(private val jockeyRepository: JockeyRepository) {

    private val range = 1..1000

    @Transactional
    fun executeBigInsertQueries() {
        for (i in range) {
            val jockey = Jockey("$i", "$i", LocalDateTime.now())
            jockeyRepository.save(jockey)
        }
    }

    @Transactional
    fun executeBigDeleteQueries() {
        for (i in range) {
            jockeyRepository.deleteById("$i")
        }
    }
}
