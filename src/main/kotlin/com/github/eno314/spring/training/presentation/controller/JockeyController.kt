package com.github.eno314.spring.training.presentation.controller

import com.github.eno314.spring.training.domain.dto.JockeyDto
import com.github.eno314.spring.training.infrastructure.entity.Jockey
import com.github.eno314.spring.training.infrastructure.mapper.JockeyMapper
import com.github.eno314.spring.training.infrastructure.repository.JockeyRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/jockey")
class JockeyController(
    private val jockeyRepository: JockeyRepository,
    private val jockeyMapper: JockeyMapper
) {

    @GetMapping
    fun getJockeys(): List<JockeyDto> {
        return jockeyRepository.findAll().map(jockeyMapper::entityToDto)
    }

    @GetMapping("/{id}")
    fun getJockey(@PathVariable id: String): JockeyDto? {
        return jockeyRepository.findById(id).map(jockeyMapper::entityToDto).orElse(null)
    }

    @PostMapping("/{id}")
    fun postJockey(@PathVariable id: String, @RequestParam name: String) {
        val jockey = Jockey(id, name, LocalDateTime.now())
        jockeyRepository.save(jockey)
    }
}
