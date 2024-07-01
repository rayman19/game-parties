package com.example.game_parties.controllers

import com.example.game_parties.services.PlayerService
import com.example.game_parties.models.Player
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/hello")
class HelloWorld {
    @GetMapping
    fun helloWorld() = "Hello World!"
}

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