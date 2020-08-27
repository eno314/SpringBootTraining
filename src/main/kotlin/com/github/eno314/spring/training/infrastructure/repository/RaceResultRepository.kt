package com.github.eno314.spring.training.infrastructure.repository

import com.github.eno314.spring.training.infrastructure.entity.RaceResult
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface RaceResultRepository : JpaRepository<RaceResult, Long> {

    @Query(
        "SELECT rr FROM RaceResult rr" +
            " INNER JOIN FETCH rr.race" +
            " INNER JOIN FETCH rr.thoroughbred" +
            " INNER JOIN FETCH rr.jockey"
    )
    fun findAllOnQuery(): List<RaceResult>
}
