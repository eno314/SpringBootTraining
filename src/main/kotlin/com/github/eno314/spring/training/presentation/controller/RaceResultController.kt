package com.github.eno314.spring.training.presentation.controller

import com.github.eno314.spring.training.domain.dto.RaceResultFindParams
import com.github.eno314.spring.training.infrastructure.entity.RaceResult
import com.github.eno314.spring.training.infrastructure.repository.RaceResultRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/race/result")
class RaceResultController(private val raceResultRepository: RaceResultRepository) {

    @GetMapping
    fun getAll(params: RaceResultFindParams): List<RaceResult> {
        return raceResultRepository.findAllOnQuery(params)
    }
}
