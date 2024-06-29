package services

import jakarta.persistence.EntityNotFoundException
import models.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

interface PartyRepository : JpaRepository<Party, Long> {

    fun findByGame(game: Game): List<Party>

    fun findByUsersContaining(user: User): List<Party>

}

@Service
class PartyService(private val partyRepository: PartyRepository) {

    fun addParty(party: Party): Party = partyRepository.save(party)

    fun deleteParty(partyId: Long) = partyRepository.deleteById(partyId)

    fun addUserToParty(partyId: Long, user: User): Party {
        val party = partyRepository.findById(partyId).orElseThrow { EntityNotFoundException() }
        // party.users.add(user)
        return partyRepository.save(party)
    }

    fun removeUserFromParty(partyId: Long, user: User): Party {
        val party = partyRepository.findById(partyId).orElseThrow { EntityNotFoundException() }
        // party.users.remove(user)
        return partyRepository.save(party)
    }

    fun findAvailablePartiesForUser(user: User): List<Party> =
        partyRepository.findByUsersContaining(user)

    fun findAvailablePartiesForUserByGame(user: User, game: Game): List<Party> =
        partyRepository.findByGame(game).filter { it.users.contains(user) }

}