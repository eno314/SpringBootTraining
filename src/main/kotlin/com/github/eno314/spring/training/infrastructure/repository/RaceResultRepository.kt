package com.github.eno314.spring.training.infrastructure.repository

import com.github.eno314.spring.training.domain.dto.RaceResultFindParams
import com.github.eno314.spring.training.infrastructure.entity.RaceResult
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface RaceResultRepository : JpaRepository<RaceResult, Long> {

    @Query(
        "SELECT rr FROM RaceResult rr" +
            " INNER JOIN FETCH rr.race" +
            " INNER JOIN FETCH rr.thoroughbred" +
            " INNER JOIN FETCH rr.jockey" +
            " WHERE (:#{#params.raceId} = null         OR rr.race.id = :#{#params.raceId})" +
            "   AND (:#{#params.thoroughbredId} = null OR rr.thoroughbred.id = :#{#params.thoroughbredId})" +
            "   AND (:#{#params.jockeyId} = null       OR rr.jockey.id = :#{#params.jockeyId})"
    )
    fun findAllOnQuery(params: RaceResultFindParams): List<RaceResult>
}
