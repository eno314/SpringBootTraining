package com.github.eno314.spring.training.infrastructure.entity

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Thoroughbred(
    @Id
    val id: String,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var updatedAt: LocalDateTime
)
