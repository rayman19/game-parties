package controllers

import models.Player
import org.springframework.web.bind.annotation.*
import services.PlayerService

@RestController
@RequestMapping("/users")
class PlayerController(private val playerService: PlayerService) {

    @PostMapping
    fun addPlayer(@RequestBody player: Player): Player = playerService.addPlayer(player)

    @DeleteMapping("/{id}")
    fun deletePlayer(@PathVariable id: Long) = playerService.deletePlayer(id)

    @PutMapping
    fun updatePlayer(@RequestBody player: Player): Player = playerService.updatePlayer(player)

    @GetMapping("/{id}")
    fun findPlayerById(@PathVariable id: Long) = playerService.findPlayerById(id)

}