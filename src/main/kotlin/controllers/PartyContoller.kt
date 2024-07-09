package com.example.game_parties.controllers

import com.example.game_parties.exceptions.GameNotFound
import com.example.game_parties.exceptions.PlayerNotFound
import com.example.game_parties.models.*
import com.example.game_parties.services.PlayerService
import com.example.game_parties.services.GameService
import com.example.game_parties.services.PartyService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/parties")
class PartyController(
    private val partyService: PartyService,
    private val playerService: PlayerService,
    private val gameService: GameService
) {

    @PostMapping("/{gameId}")
    fun createParty(@PathVariable gameId: Long): ResponseEntity<Party> {
        val game = gameService.findGameById(gameId)
            .orElseThrow { GameNotFound }

        val party = Party(
            game = game,
            players = mutableListOf()
        )

        val savedParty = partyService.addParty(party)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedParty)
    }

    @DeleteMapping("/{id}")
    fun deleteParty(@PathVariable id: Long): ResponseEntity<String> {
        partyService.deleteParty(id)
        return ResponseEntity.ok("Party с id $id успешно удалена")
    }

    @GetMapping("/{id}")
    fun findPartyById(@PathVariable id: Long): ResponseEntity<Optional<Party>> {
        val party = partyService.findPartyById(id)
        return ResponseEntity.ok(party)
    }

    @PutMapping("/{partyId}/users/{playerId}")
    fun addPlayerToParty(@PathVariable partyId: Long, @PathVariable playerId: Long): ResponseEntity<String> {
        val updatedParty = partyService.addPlayerToParty(partyId, playerId)
        println(updatedParty)
        return ResponseEntity.ok("Игрок с id $playerId успешно добавлен в Party с id $partyId")
    }

    @DeleteMapping("/{partyId}/users/{playerId}")
    fun removePlayerFromParty(@PathVariable partyId: Long, @PathVariable playerId: Long): ResponseEntity<String> {
        val updatedParty = partyService.removePlayerFromParty(partyId, playerId)
        return ResponseEntity.ok("Игрок с id $playerId успешно удален из Party с id $partyId")
    }

    @GetMapping("/available/{playerId}")
    fun findAvailablePartiesForPlayer(@PathVariable playerId: Long): ResponseEntity<List<Party>> {
        val user = playerService.findPlayerById(playerId).orElseThrow { PlayerNotFound }
        val availableParties = partyService.findAvailablePartiesForPlayer(user)
        return ResponseEntity.ok(availableParties)
    }

    @GetMapping("/available/{playerId}/by-game/{gameId}")
    fun findAvailablePartiesForPlayerByGame(@PathVariable playerId: Long, @PathVariable gameId: Long): ResponseEntity<List<Party>> {
        val user = playerService.findPlayerById(playerId).orElseThrow { PlayerNotFound }
        val game = gameService.findGameById(gameId).orElseThrow { GameNotFound }
        val availableParties = partyService.findAvailablePartiesForPlayerByGame(user, game)
        return ResponseEntity.ok(availableParties)
    }
}