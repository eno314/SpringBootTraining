package com.github.eno314.spring.training.presentation.controller

import com.github.eno314.spring.training.domain.service.DebugPerformanceService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/debug")
class DebugController(private val performanceService: DebugPerformanceService) {

    @GetMapping("/performance/bigInsertQueries")
    fun getPerformanceByBigInsertQueries(): String {
        return getPerformance { performanceService.executeBigInsertQueries() }
    }

    @GetMapping("/performance/bigDeleteQueries")
    fun getPerformanceByBigDeleteQueries(): String {
        return getPerformance { performanceService.executeBigDeleteQueries() }
    }

    private fun getPerformance(process: () -> Unit): String {
        val startTimestamp = System.currentTimeMillis()
        process()
        val duration = System.currentTimeMillis() - startTimestamp
        return "spending time : $duration ms."
    }
}
