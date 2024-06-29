package controllers

import models.Party
import models.User
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.web.bind.annotation.*
import services.GameService
import services.PartyService
import services.UserService

@RestController
@RequestMapping("/parties")
class PartyController(
    private val partyService: PartyService,
    private val userService: UserService,
    private val gameService: GameService
) {

    @PostMapping
    fun addParty(@RequestBody party: Party): Party = partyService.addParty(party)

    @DeleteMapping("/{id}")
    fun deleteParty(@PathVariable id: Long) = partyService.deleteParty(id)

    @PutMapping("/{partyId}/users")
    fun addUserToParty(@PathVariable partyId: Long, @RequestBody user: User): Party =
        partyService.addUserToParty(partyId, user)

    @DeleteMapping("/{partyId}/users")
    fun removeUserFromParty(@PathVariable partyId: Long, @RequestBody user: User): Party =
        partyService.removeUserFromParty(partyId, user)

    @GetMapping("/available")
    fun findAvailablePartiesForUser(@RequestParam userId: Long): List<Party> {
        val user = userService.findById(userId).orElseThrow { ChangeSetPersister.NotFoundException() }
        return partyService.findAvailablePartiesForUser(user)
    }

    @GetMapping("/available/by-game")
    fun findAvailablePartiesForUserByGame(@RequestParam userId: Long, @RequestParam gameId: Long): List<Party> {
        val user = userService.findById(userId).orElseThrow { ChangeSetPersister.NotFoundException() }
        val game = gameService.findById(gameId).orElseThrow { ChangeSetPersister.NotFoundException() }
        return partyService.findAvailablePartiesForUserByGame(user, game)
    }

}