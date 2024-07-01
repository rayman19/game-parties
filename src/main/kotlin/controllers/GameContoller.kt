package controllers

import models.Game
import org.springframework.web.bind.annotation.*
import services.GameService

@RestController
@RequestMapping("/games")
class GameController(private val gameService: GameService) {

    @PostMapping
    fun addGame(@RequestBody game: Game): Game = gameService.addGame(game)

    @DeleteMapping("/{id}")
    fun deleteGame(@PathVariable id: Long) = gameService.deleteGame(id)

    @PutMapping
    fun updateGame(@RequestBody game: Game): Game = gameService.updateGame(game)

    @GetMapping("/{id}")
    fun findGameById(@PathVariable id: Long) = gameService.findGameById(id)

}