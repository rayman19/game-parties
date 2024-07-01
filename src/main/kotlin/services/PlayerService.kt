package com.example.game_parties.services

import com.example.game_parties.models.Player
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service
import java.util.*

interface PlayerRepository : JpaRepository<Player, Long>

@Service
class PlayerService(private val playerRepository: PlayerRepository) {

    fun addPlayer(player: Player): Player = playerRepository.save(player)

    fun deletePlayer(playerId: Long) = playerRepository.deleteById(playerId)

    fun updatePlayer(player: Player): Player = playerRepository.save(player)

    fun findPlayerById(playerId: Long): Optional<Player> = playerRepository.findById(playerId)

}