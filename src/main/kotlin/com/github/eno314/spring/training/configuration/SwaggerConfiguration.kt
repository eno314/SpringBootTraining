package com.github.eno314.spring.training.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import java.util.function.Predicate

@Configuration
class SwaggerConfiguration {

    @Bean
    fun springTrainingApi(): Docket {
        return Docket(DocumentationType.OAS_30)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(Predicate.not(PathSelectors.regex("/error")))
            .build()
    }
}
