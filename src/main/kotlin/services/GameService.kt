package services

import models.Game
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service
import java.util.*

interface GameRepository : JpaRepository<Game, Long>

@Service
class GameService(private val gameRepository: GameRepository) {

    fun addGame(game: Game): Game = gameRepository.save(game)

    fun deleteGame(gameId: Long) = gameRepository.deleteById(gameId)

    fun updateGame(game: Game): Game = gameRepository.save(game)

    fun findGameById(gameId: Long): Optional<Game> = gameRepository.findById(gameId)

}