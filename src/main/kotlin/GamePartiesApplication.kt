package com.example.game_parties

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@SpringBootApplication
class GamePartiesApplication {
	@GetMapping
	fun helloWorld() = "Hello World!"
}

fun main(args: Array<String>) {
	runApplication<GamePartiesApplication>(*args)
}