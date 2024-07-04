package com.example.game_parties.controllers

import com.example.game_parties.services.PlayerService
import com.example.game_parties.models.Player
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.Optional

@RestController
@RequestMapping("/hello")
class HelloWorld {
    @GetMapping
    fun helloWorld(): ResponseEntity<String> {
        return ResponseEntity.ok("Hello World!")
    }
}

@RestController
@RequestMapping("/players")
class PlayerController(private val playerService: PlayerService) {

    @PostMapping
    fun addPlayer(@RequestBody player: Player): ResponseEntity<String> {
        val addedPlayer = playerService.addPlayer(player)
        return ResponseEntity.ok("Player успешно добавлен с id: ${addedPlayer.id}")
    }

    @DeleteMapping("/{id}")
    fun deletePlayer(@PathVariable id: Long): ResponseEntity<String> {
        playerService.deletePlayer(id)
        return ResponseEntity.ok("Player с id $id успешно удален")
    }

    @PutMapping
    fun updatePlayer(@RequestBody player: Player): ResponseEntity<String> {
        val updatedPlayer = playerService.updatePlayer(player)
        return ResponseEntity.ok("Player успешно обновлен")
    }

    @GetMapping("/{id}")
    fun findPlayerById(@PathVariable id: Long): ResponseEntity<Optional<Player>> {
        val player = playerService.findPlayerById(id)
        return ResponseEntity.ok(player)
    }
}