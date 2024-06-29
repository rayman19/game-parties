package services

import models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service
import java.util.*

interface UserRepository : JpaRepository<User, Long>

@Service
class UserService(private val userRepository: UserRepository) {

    fun addUser(user: User): User = userRepository.save(user)

    fun deleteUser(userId: Long) = userRepository.deleteById(userId)

    fun updateUser(user: User): User = userRepository.save(user)

    fun findById(userId: Long): Optional<User> = userRepository.findById(userId)

}