package com.github.eno314.spring.training.presentation.controller;

import com.github.eno314.spring.training.domain.service.HelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
@RequiredArgsConstructor
public class HelloController {

    @Qualifier("HelloJavaService")
    private final HelloService helloService;

    @GetMapping("/language")
    public String getLanguage() {
        return helloService.language();
    }
}
