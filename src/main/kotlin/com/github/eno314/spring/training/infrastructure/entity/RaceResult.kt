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
data class RaceResult(

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    val id: Long = 0,

    @Column(nullable = false)
    var result: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "raceId", nullable = false)
    var race: Race,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "thoroughbredId", nullable = false)
    var thoroughbred: Thoroughbred,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jockeyId", nullable = false)
    var jockey: Jockey,

    @Column(nullable = false)
    var updatedAt: LocalDateTime
)
