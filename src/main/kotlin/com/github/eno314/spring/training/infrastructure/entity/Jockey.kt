package com.github.eno314.spring.training.infrastructure.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Jockey(
    @Id
    val id: String,

    @Column(nullable = false)
    val name: String
)
