package controllers

import models.User
import org.springframework.web.bind.annotation.*
import services.UserService

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @PostMapping
    fun addUser(@RequestBody user: User): User = userService.addUser(user)

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long) = userService.deleteUser(id)

    @PutMapping
    fun updateUser(@RequestBody user: User): User = userService.updateUser(user)

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long) = userService.findById(id)

}