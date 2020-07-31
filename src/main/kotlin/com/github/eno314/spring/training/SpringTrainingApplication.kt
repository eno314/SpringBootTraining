package com.github.eno314.spring.training

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringTrainingApplication

fun main(args: Array<String>) {
    runApplication<SpringTrainingApplication>(*args)
}
