package com.github.eno314.spring.training.infrastructure.mapper;

import com.github.eno314.spring.training.domain.dto.JockeyDto;
import com.github.eno314.spring.training.infrastructure.entity.Jockey;
import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JockeyMapper {

    JockeyMapper INSTANCE = Mappers.getMapper(JockeyMapper.class);

    JockeyDto entityToDto(Jockey jockey);

    Jockey dtoToEntity(JockeyDto jockeyDto, @MappingTarget Jockey jockey);
}
