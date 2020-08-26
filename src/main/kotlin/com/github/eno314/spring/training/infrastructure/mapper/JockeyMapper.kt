package com.github.eno314.spring.training.infrastructure.mapper

import com.github.eno314.spring.training.domain.dto.JockeyDto
import com.github.eno314.spring.training.infrastructure.entity.Jockey
import org.mapstruct.Mapper
import org.mapstruct.MappingTarget
import org.mapstruct.factory.Mappers

@Mapper(componentModel = "spring")
interface JockeyMapper {
    fun entityToDto(jockey: Jockey): JockeyDto

    fun dtoToEntity(jockeyDto: JockeyDto): Jockey

    fun dtoToEntity(jockeyDto: JockeyDto, @MappingTarget jockey: Jockey): Jockey

    companion object {
        val INSTANCE: JockeyMapper = Mappers.getMapper(JockeyMapper::class.java)
    }
}
