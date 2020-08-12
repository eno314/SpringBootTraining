package com.github.eno314.spring.training.configuration

import org.modelmapper.ModelMapper
import org.modelmapper.convention.NameTransformers
import org.modelmapper.convention.NamingConventions
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ModelMapperConfiguration {

    @Bean
    fun modelMapper() = ModelMapper()

    @Bean
    fun builderModelMapper() = ModelMapper().apply {
        configuration.apply {
            destinationNameTransformer = NameTransformers.builder()
            destinationNamingConvention = NamingConventions.builder()
        }
    }
}
