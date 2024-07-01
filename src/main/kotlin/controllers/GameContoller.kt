package com.example.game_parties.controllers

import com.example.game_parties.models.Game
import com.example.game_parties.services.GameService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/games")
class GameController(private val gameService: GameService) {

    @PostMapping
    fun addGame(@RequestBody @Valid game: Game): Game = gameService.addGame(game)

    @DeleteMapping("/{id}")
    fun deleteGame(@PathVariable id: Long) = gameService.deleteGame(id)

    @PutMapping
    fun updateGame(@RequestBody game: Game): Game = gameService.updateGame(game)

    @GetMapping("/{id}")
    fun findGameById(@PathVariable id: Long) = gameService.findGameById(id)

    /* TODO Попробывать реализовать все на ResponseEntity
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(ex: MethodArgumentNotValidException): ResponseEntity<String> {
        val errors = ex.bindingResult.allErrors.map { it.defaultMessage }
        val errorMessage = "Ошибка валидации: ${errors.joinToString(", ")}"
        return ResponseEntity.badRequest().body(errorMessage)
    }*/
}