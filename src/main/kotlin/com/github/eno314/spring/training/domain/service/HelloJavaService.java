package com.github.eno314.spring.training.domain.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service("HelloJavaService")
public class HelloJavaService implements HelloService {

    @NotNull
    @Override
    public String language() {
        return "java";
    }
}
