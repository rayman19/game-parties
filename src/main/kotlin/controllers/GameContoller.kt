package com.example.game_parties.controllers

import com.example.game_parties.models.Game
import com.example.game_parties.services.GameService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.Optional

@RestController
@RequestMapping("/games")
class GameController(private val gameService: GameService) {

    @PostMapping
    fun addGame(@RequestBody @Valid game: Game): ResponseEntity<String> {
        val addedGame = gameService.addGame(game)
        return ResponseEntity.ok("Game успешно добавлена с id: ${addedGame.id}")
    }

    @DeleteMapping("/{id}")
    fun deleteGame(@PathVariable id: Long): ResponseEntity<String> {
        gameService.deleteGame(id)
        return ResponseEntity.ok("Game с id $id успешно удалена")
    }

    @PutMapping
    fun updateGame(@RequestBody game: Game): ResponseEntity<String> {
        val updatedGame = gameService.updateGame(game)
        return ResponseEntity.ok("Game успешно обновлена")
    }

    @GetMapping("/{id}")
    fun findGameById(@PathVariable id: Long): ResponseEntity<Optional<Game>> {
        val game = gameService.findGameById(id)
        return ResponseEntity.ok(game)
    }
}