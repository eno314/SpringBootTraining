package com.github.eno314.spring.training.infrastructure.entity

import com.github.pozo.KotlinBuilder
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
@KotlinBuilder
data class Jockey(
    @Id
    val id: String,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var updatedAt: LocalDateTime,
)
