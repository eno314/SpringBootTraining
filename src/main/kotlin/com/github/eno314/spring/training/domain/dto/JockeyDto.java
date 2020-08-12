package com.github.eno314.spring.training.domain.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Builder
@Value
public class JockeyDto {

    String id;

    String name;

    LocalDateTime updatedAt;
}
