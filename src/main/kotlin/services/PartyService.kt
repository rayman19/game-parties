package services

import jakarta.persistence.EntityNotFoundException
import models.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

interface PartyRepository : JpaRepository<Party, Long> {

    fun findByGame(game: Game): List<Party>

    fun findByPlayersContaining(player: Player): List<Party>

}

@Service
class PartyService(private val partyRepository: PartyRepository) {

    fun addParty(party: Party): Party = partyRepository.save(party)

    fun deleteParty(partyId: Long) = partyRepository.deleteById(partyId)

    fun addPlayerToParty(partyId: Long, player: Player): Party {
        val party = partyRepository.findById(partyId).orElseThrow { EntityNotFoundException() }
        // party.users.add(user)
        return partyRepository.save(party)
    }

    fun removePlayerFromParty(partyId: Long, player: Player): Party {
        val party = partyRepository.findById(partyId).orElseThrow { EntityNotFoundException() }
        // party.users.remove(user)
        return partyRepository.save(party)
    }

    fun findAvailablePartiesForPlayer(player: Player): List<Party> =
        partyRepository.findByPlayersContaining(player)

    fun findAvailablePartiesForPlayerByGame(player: Player, game: Game): List<Party> =
        partyRepository.findByGame(game).filter { it.players.contains(player) }

}