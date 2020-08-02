package com.github.eno314.spring.training.infrastructure.entity

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class JockeyResult(

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    val id: Long,

    @Column(nullable = false)
    val result: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jockeyId", nullable = false)
    val jockey: Jockey,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "raceId", nullable = false)
    val race: Race,

    @Column(nullable = false)
    val updatedAt: LocalDateTime
)
