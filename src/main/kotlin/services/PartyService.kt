package com.example.game_parties.services

import com.example.game_parties.exceptions.PartyNotFound
import com.example.game_parties.exceptions.PlayerNotFound
import com.example.game_parties.models.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service
import java.util.*

interface PartyRepository : JpaRepository<Party, Long> {

    fun findByGame(game: Game): List<Party>

    fun findByPlayersContaining(player: Player): List<Party>

}

@Service
class PartyService(
    private val partyRepository: PartyRepository,
    private val playerService: PlayerService
) {

    fun addParty(party: Party): Party = partyRepository.save(party)

    fun deleteParty(partyId: Long) = partyRepository.deleteById(partyId)

    fun findPartyById(partyId: Long): Optional<Party> = partyRepository.findById(partyId)

    fun findPlayerInParty(party: Party, playerId: Long): Player? =
        party.players.find { it.id == playerId }

    fun addPlayerToParty(partyId: Long, playerId: Long): Party {
        val party = findPartyById(partyId).orElseThrow { PartyNotFound }
        val partyPlayer = findPlayerInParty(party, playerId)
        if (partyPlayer == null) {
            val newPlayer = playerService.findPlayerById(playerId).orElseThrow { PlayerNotFound }
            val updatedParty = party.copy(players = party.players + newPlayer)
            partyRepository.save(updatedParty)
            return updatedParty
        }
        return party
    }

    fun removePlayerFromParty(partyId: Long, playerId: Long): Party {
        val party = partyRepository.findById(partyId).orElseThrow { PartyNotFound }
        val findedPlayer = findPlayerInParty(party, playerId)
        if (findedPlayer == null) {
            PlayerNotFound
            return party
        }
        else {
            val updatedParty = party.copy(players = party.players - findedPlayer)
            partyRepository.save(updatedParty)
            return updatedParty
        }
    }

    fun findAvailablePartiesForPlayer(player: Player): List<Party> =
        partyRepository.findByPlayersContaining(player)

    fun findAvailablePartiesForPlayerByGame(player: Player, game: Game): List<Party> =
        partyRepository.findByGame(game).filter { it.players.contains(player) }

}