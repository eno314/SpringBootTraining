package com.github.eno314.spring.training.domain.service

import com.github.eno314.spring.training.infrastructure.entity.Jockey
import com.github.eno314.spring.training.infrastructure.entity.Race
import com.github.eno314.spring.training.infrastructure.entity.RaceResult
import com.github.eno314.spring.training.infrastructure.entity.Thoroughbred
import com.github.eno314.spring.training.infrastructure.repository.JockeyRepository
import com.github.eno314.spring.training.infrastructure.repository.RaceRepository
import com.github.eno314.spring.training.infrastructure.repository.RaceResultRepository
import com.github.eno314.spring.training.infrastructure.repository.ThoroughbredRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class DebugTestDataService(
    private val jockeyRepository: JockeyRepository,
    private val raceRepository: RaceRepository,
    private val raceResultRepository: RaceResultRepository,
    private val thoroughbredRepository: ThoroughbredRepository
) {

    @Transactional
    fun create() {
        for (i in 1..10) {
            jockeyRepository.save(createJockey(i))
            raceRepository.save(createRace(i))
            thoroughbredRepository.save(createThoroughbred(i))
        }

        for (i in 1..1000) {
            val race = raceRepository.save(createRace(i))

            for (j in 1..10) {
                val jockey = jockeyRepository.findById("jockey_$j").orElseThrow()
                val thoroughbred = thoroughbredRepository.findById("thoroughbred_$j").orElseThrow()
                val raceResult = RaceResult(
                    result = j,
                    race = race,
                    jockey = jockey,
                    thoroughbred = thoroughbred,
                    updatedAt = LocalDateTime.now()
                )
                raceResultRepository.save(raceResult)
            }
        }
    }

    private fun createJockey(num: Int) = Jockey("jockey_$num", "jockey_name_$num", LocalDateTime.now())

    private fun createRace(num: Int) = Race(num.toLong(), name = "race_name_$num", updatedAt = LocalDateTime.now())

    private fun createThoroughbred(num: Int) =
        Thoroughbred("thoroughbred_$num", "thoroughbred_name_$num", LocalDateTime.now())
}
