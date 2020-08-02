package com.github.eno314.spring.training.infrastructure.entity

import com.github.eno314.spring.training.domain.value.CourseType
import com.github.eno314.spring.training.domain.value.Grade
import com.github.eno314.spring.training.domain.value.Racecourse
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Race(

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    val id: Long,

    @Column(nullable = false)
    val year: Int,

    @Column(nullable = false, length = 32)
    @Enumerated(EnumType.STRING)
    val racecourse: Racecourse,

    // 開催数（第N回）
    @Column(nullable = false)
    val eventCount: Int,

    // 開催日数（第M日目）
    @Column(nullable = false)
    val dayCount: Int,

    @Column(nullable = false)
    val raceNo: Int,

    @Column(nullable = false)
    val distance: Int,

    @Column(nullable = false, length = 16)
    @Enumerated(EnumType.STRING)
    val grade: Grade,

    @Column(nullable = false, length = 16)
    @Enumerated(EnumType.STRING)
    val courseType: CourseType,

    @Column(nullable = false)
    val updatedAt: LocalDateTime
)
