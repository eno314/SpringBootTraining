package com.github.eno314.spring.training.domain.dto

import com.github.eno314.spring.training.infrastructure.entity.Jockey
import com.github.eno314.spring.training.infrastructure.mapper.JockeyMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class JockeyDtoTest {

    @Nested
    inner class MapperTest {

        private val jockeyMapper = JockeyMapper.INSTANCE

        @Test
        fun jockeyShouldMapToJockeyDto() {
            val updatedAt = LocalDateTime.now()
            val jockey = Jockey("id", "name", updatedAt)

            val actual = jockeyMapper.entityToDto(jockey)

            val expected = JockeyDto.builder()
                .id("id")
                .name("name")
                .updatedAt(updatedAt)
                .build()
            assertThat(actual).isEqualTo(expected)
        }

        @Test
        fun jockeyDtoShouldMapJockey() {
            val updatedAt = LocalDateTime.now()
            val jockeyDto = JockeyDto.builder()
                .id("id")
                .name("name")
                .updatedAt(updatedAt)
                .build()
            val jockey = Jockey("id", "", LocalDateTime.MIN)

            val actual = jockeyMapper.dtoToEntity(jockeyDto, jockey)

            val expected = Jockey("id", "name", updatedAt)
            assertThat(actual).isEqualTo(expected)
        }
    }
}
