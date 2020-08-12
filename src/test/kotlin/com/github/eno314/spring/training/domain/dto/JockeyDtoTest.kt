package com.github.eno314.spring.training.domain.dto

import com.github.eno314.spring.training.configuration.ModelMapperConfiguration
import com.github.eno314.spring.training.infrastructure.entity.Jockey
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [ModelMapperConfiguration::class])
class JockeyDtoTest {

    @Autowired
    @Qualifier("builderModelMapper")
    private lateinit var builderModelMapper: ModelMapper

    @Nested
    inner class ModelMapperForBuilderTest {

        @Test
        fun jockeyShouldMapToJockeyDto() {
            val updatedAt = LocalDateTime.now()
            val jockey = Jockey("id", "name", updatedAt)

            val actual = builderModelMapper.map(jockey, JockeyDto.JockeyDtoBuilder::class.java).build()

            val expected = JockeyDto.builder()
                .id("id")
                .name("name")
                .updatedAt(updatedAt)
                .build()
            assertThat(actual).isEqualTo(expected)
        }
    }
}
