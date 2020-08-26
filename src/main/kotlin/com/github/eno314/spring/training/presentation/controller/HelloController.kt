package com.github.eno314.spring.training.presentation.controller

import com.github.eno314.spring.training.domain.service.HelloService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hello")
class HelloController(
    @Qualifier("HelloJavaService")
    private val helloService: HelloService
) {

    @GetMapping("/language")
    fun language(): String = helloService.language()
}
