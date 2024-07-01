package controllers

import com.example.game_parties.models.*
import com.example.game_parties.services.PlayerService
import com.example.game_parties.services.GameService
import com.example.game_parties.services.PartyService
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/parties")
class PartyController(
    private val partyService: PartyService,
    private val playerService: PlayerService,
    private val gameService: GameService
) {

    @PostMapping
    fun addParty(@RequestBody party: Party): Party = partyService.addParty(party)

    @DeleteMapping("/{id}")
    fun deleteParty(@PathVariable id: Long) = partyService.deleteParty(id)

    @PutMapping("/{partyId}/users")
    fun addPlayerToParty(@PathVariable partyId: Long, @RequestBody player: Player): Party =
        partyService.addPlayerToParty(partyId, player)

    @DeleteMapping("/{partyId}/users")
    fun removePlayerFromParty(@PathVariable partyId: Long, @RequestBody player: Player): Party =
        partyService.removePlayerFromParty(partyId, player)

    @GetMapping("/available")
    fun findAvailablePartiesForPlayer(@RequestParam playerId: Long): List<Party> {
        val user = playerService.findPlayerById(playerId).orElseThrow { ChangeSetPersister.NotFoundException() }
        return partyService.findAvailablePartiesForPlayer(user)
    }

    @GetMapping("/available/by-game")
    fun findAvailablePartiesForPlayerByGame(@RequestParam playerId: Long, @RequestParam gameId: Long): List<Party> {
        val user = playerService.findPlayerById(playerId).orElseThrow { ChangeSetPersister.NotFoundException() }
        val game = gameService.findGameById(gameId).orElseThrow { ChangeSetPersister.NotFoundException() }
        return partyService.findAvailablePartiesForPlayerByGame(user, game)
    }

}