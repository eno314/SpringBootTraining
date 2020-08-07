package com.github.eno314.spring.training.domain.service

import org.springframework.stereotype.Service

@Service("HelloKotlinService")
class HelloKotlinService : HelloService {

    override fun language(): String = "kotlin"
}
